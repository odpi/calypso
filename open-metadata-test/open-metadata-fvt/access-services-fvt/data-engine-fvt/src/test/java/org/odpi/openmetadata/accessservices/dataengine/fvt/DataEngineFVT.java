/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.dataengine.fvt;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.odpi.openmetadata.accessservices.dataengine.ProcessSetupService;
import org.odpi.openmetadata.accessservices.dataengine.RepositoryService;
import org.odpi.openmetadata.accessservices.dataengine.client.DataEngineClient;
import org.odpi.openmetadata.accessservices.dataengine.model.SoftwareServerCapability;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.EntityDetail;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Relationship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DataEngineFVT {

    private static final String DESCRIPTION = "description";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String VERSION = "version";
    private static final String PATCH_LEVEL = "patchLevel";
    private static final String QUALIFIED_NAME = "qualifiedName";
    private static final String SOURCE = "source";

    private final ProcessSetupService processSetupService = new ProcessSetupService();

    @ParameterizedTest
    @MethodSource("org.odpi.openmetadata.accessservices.dataengine.PlatformConnectionProvider#getConnectionDetails")
    public void registerExternalTool(String userId, DataEngineClient dataEngineClient, RepositoryService repositoryService)
            throws InvalidParameterException, UserNotAuthorizedException, PropertyServerException, ConnectorCheckedException {

        SoftwareServerCapability softwareServerCapability = processSetupService.createExternalDataEngine(userId, dataEngineClient);

        Optional<EntityDetail> entityDetail = repositoryService.findSoftwareServerCapabilityByPropertyValue(softwareServerCapability.getQualifiedName());

        if(!entityDetail.isPresent()) {
            fail();
        }

        EntityDetail entity = entityDetail.get();
        assertEquals(softwareServerCapability.getDescription(), entity.getProperties().getPropertyValue(DESCRIPTION).valueAsString());
        assertEquals(softwareServerCapability.getDisplayName(), entity.getProperties().getPropertyValue(NAME).valueAsString());
        assertEquals(softwareServerCapability.getEngineType(), entity.getProperties().getPropertyValue(TYPE).valueAsString());
        assertEquals(softwareServerCapability.getEngineVersion(), entity.getProperties().getPropertyValue(VERSION).valueAsString());
        assertEquals(softwareServerCapability.getPatchLevel(), entity.getProperties().getPropertyValue(PATCH_LEVEL).valueAsString());
        assertEquals(softwareServerCapability.getQualifiedName(), entity.getProperties().getPropertyValue(QUALIFIED_NAME).valueAsString());
        assertEquals(softwareServerCapability.getSource(), entity.getProperties().getPropertyValue(SOURCE).valueAsString());

    }

    @ParameterizedTest
    @MethodSource("org.odpi.openmetadata.accessservices.dataengine.PlatformConnectionProvider#getConnectionDetails")
    public void verifyLineageMappingsForAJobProcess(String userId, DataEngineClient dataEngineClient, RepositoryService repositoryService)
            throws InvalidParameterException, UserNotAuthorizedException, PropertyServerException, ConnectorCheckedException {

        processSetupService.createExternalDataEngine(userId, dataEngineClient);
        processSetupService.createJobProcessWithContent(userId, dataEngineClient);

        Map<String, List<String>> columnLineages = processSetupService.getJobProcessLineageMappingsProxiesByCsvColumn();

        for(String columnName : columnLineages.keySet()) {
            List<String> attributes = columnLineages.get(columnName);
            String previousAttribute = null;
            for (int i = 0; i < attributes.size() - 1; i++) {
                String currentAttribute = attributes.get(i);
                String entityGUID = repositoryService.findEntityGUIDByQualifiedName(currentAttribute);
                List<Relationship> relationships = repositoryService.findRelationshipsByGUID(entityGUID);
                List<String> lineageMappingOtherProxyQualifiedName = repositoryService.getLineageMappingsProxiesQualifiedNames(relationships, currentAttribute);
                List<String> expectedLineageMappings = new ArrayList<>();
                if(previousAttribute != null) {
                    expectedLineageMappings.add(previousAttribute);
                }
                expectedLineageMappings.add(attributes.get(i + 1));
                Collections.sort(expectedLineageMappings);
                Collections.sort(lineageMappingOtherProxyQualifiedName);
                assertEquals(expectedLineageMappings, lineageMappingOtherProxyQualifiedName);
                previousAttribute = currentAttribute;
            }
        }
    }
}
