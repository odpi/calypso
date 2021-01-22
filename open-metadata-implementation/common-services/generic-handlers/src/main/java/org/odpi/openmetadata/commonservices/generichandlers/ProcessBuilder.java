/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.generichandlers;

import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryHelper;

import java.util.Map;

/**
 * ProcessBuilder creates the parts of a root repository entity for a process.  The default type of this process
 * is DeployedSoftwareComponent.
 */
public class ProcessBuilder extends AssetBuilder
{
    private String formula                = null;
    private String implementationLanguage = null;

    /**
     * Creation constructor used when working with classifications
     *
     * @param repositoryHelper helper methods
     * @param serviceName name of this OMAS
     * @param serverName name of local server
     */
    ProcessBuilder(OMRSRepositoryHelper repositoryHelper,
                   String               serviceName,
                   String               serverName)
    {
        super(OpenMetadataAPIMapper.DEPLOYED_SOFTWARE_COMPONENT_TYPE_GUID,
              OpenMetadataAPIMapper.DEPLOYED_SOFTWARE_COMPONENT_TYPE_NAME,
              repositoryHelper,
              serviceName,
              serverName);
    }


    /**
     * Constructor supporting all entity properties. (Classifications are added separately.)
     *
     * @param qualifiedName unique name
     * @param technicalName new value for the name
     * @param technicalDescription new description for the process
     * @param formula description of the logic that is implemented by this process
     * @param implementationLanguage language used to implement this process (DeployedSoftwareComponent and subtypes only)
     * @param additionalProperties additional properties
     * @param typeGUID unique identifier for the type of this process
     * @param typeName unique name for the type of this process
     * @param extendedProperties  properties from the subtype
     * @param repositoryHelper helper methods
     * @param serviceName name of this OMAS
     * @param serverName name of local server
     */
    protected ProcessBuilder(String qualifiedName,
                             String technicalName,
                             String technicalDescription,
                             String formula,
                             String implementationLanguage,
                             Map<String, String> additionalProperties,
                             String typeGUID,
                             String typeName,
                             Map<String, Object> extendedProperties,
                             OMRSRepositoryHelper repositoryHelper,
                             String serviceName,
                             String serverName) {
        super(qualifiedName,
                technicalName,
                technicalDescription,
                additionalProperties,
                typeGUID,
                typeName,
                extendedProperties,
                repositoryHelper,
                serviceName,
                serverName);

        this.formula = formula;
        this.implementationLanguage = implementationLanguage;
    }


    /**
     * Return the bean properties describing the data flow relationship.
     *
     * @param qualifiedName unique name of this relationship
     * @param description description of this relationship
     * @param formula logic describing any filtering of data
     * @param methodName name of the calling method
     * @return InstanceProperties object
     */
    InstanceProperties getDataFlowProperties(String qualifiedName,
                                             String description,
                                             String formula,
                                             String methodName)
    {
        InstanceProperties properties = null;

        if (qualifiedName != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      null,
                                                                      OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                                                      qualifiedName,
                                                                      methodName);
        }

        if (description != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.DESCRIPTION_PROPERTY_NAME,
                                                                      description,
                                                                      methodName);
        }

        if (formula != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.FORMULA_PROPERTY_NAME,
                                                                      formula,
                                                                      methodName);
        }

        return properties;
    }


    /**
     * Return the bean properties describing the control flow relationship.
     *
     * @param qualifiedName unique name of this relationship
     * @param description description of this relationship
     * @param guard logic describing what must be true for control to pass down this control flow
     * @param methodName name of the calling method
     * @return InstanceProperties object
     */
    InstanceProperties getControlFlowProperties(String qualifiedName,
                                                String description,
                                                String guard,
                                                String methodName)
    {
        InstanceProperties properties = null;

        if (qualifiedName != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      null,
                                                                      OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                                                      qualifiedName,
                                                                      methodName);
        }

        if (description != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.DESCRIPTION_PROPERTY_NAME,
                                                                      description,
                                                                      methodName);
        }

        if (guard != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.GUARD_PROPERTY_NAME,
                                                                      guard,
                                                                      methodName);
        }

        return properties;
    }


    /**
     * Return the bean properties describing the process call relationship.
     *
     * @param qualifiedName unique name of this relationship
     * @param description description of this relationship
     * @param formula logic describing any filtering of data on the call
     * @param methodName name of the calling method
     * @return InstanceProperties object
     */
    InstanceProperties getProcessCallProperties(String qualifiedName,
                                                String description,
                                                String formula,
                                                String methodName)
    {
        InstanceProperties properties = null;

        if (qualifiedName != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      null,
                                                                      OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                                                      qualifiedName,
                                                                      methodName);
        }

        if (description != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.DESCRIPTION_PROPERTY_NAME,
                                                                      description,
                                                                      methodName);
        }

        if (formula != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.FORMULA_PROPERTY_NAME,
                                                                      formula,
                                                                      methodName);
        }

        return properties;
    }

    /**
     * Return the supplied bean properties in an InstanceProperties object.
     *
     * @param methodName name of the calling method
     * @return InstanceProperties object
     * @throws InvalidParameterException there is a problem with the properties
     */
    @Override
    public InstanceProperties getInstanceProperties(String  methodName) throws InvalidParameterException
    {
        InstanceProperties properties = super.getInstanceProperties(methodName);

        if (formula != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.FORMULA_PROPERTY_NAME,
                                                                      formula,
                                                                      methodName);
        }

        if (implementationLanguage != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.IMPLEMENTATION_LANGUAGE_PROPERTY_NAME,
                                                                      implementationLanguage,
                                                                      methodName);
        }

        return properties;
    }
}
