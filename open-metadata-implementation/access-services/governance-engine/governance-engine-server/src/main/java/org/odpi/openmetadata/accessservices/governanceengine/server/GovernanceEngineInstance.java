/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.governanceengine.server;


import org.odpi.openmetadata.accessservices.governanceengine.connectors.outtopic.GovernanceEngineOutTopicClientProvider;
import org.odpi.openmetadata.accessservices.governanceengine.converters.GovernanceActionConverter;
import org.odpi.openmetadata.accessservices.governanceengine.converters.MetadataElementConverter;
import org.odpi.openmetadata.accessservices.governanceengine.ffdc.GovernanceEngineErrorCode;
import org.odpi.openmetadata.accessservices.governanceengine.handlers.GovernanceConfigurationHandler;
import org.odpi.openmetadata.accessservices.governanceengine.handlers.MetadataElementHandler;
import org.odpi.openmetadata.accessservices.governanceengine.metadataelements.GovernanceActionElement;
import org.odpi.openmetadata.accessservices.governanceengine.metadataelements.MetadataElement;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.commonservices.generichandlers.GovernanceActionHandler;
import org.odpi.openmetadata.commonservices.multitenant.OMASServiceInstance;
import org.odpi.openmetadata.commonservices.multitenant.ffdc.exceptions.NewInstanceException;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.odpi.openmetadata.frameworks.governanceaction.properties.OpenMetadataElement;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;

import java.util.List;

/**
 * GovernanceEngineInstance caches references to OMRS objects for a specific server.
 * It is also responsible for registering itself in the instance map.
 * It is created by the admin class during server start up and
 */
public class GovernanceEngineInstance extends OMASServiceInstance
{
    private static AccessServiceDescription myDescription = AccessServiceDescription.GOVERNANCE_ENGINE_OMAS;

    private GovernanceConfigurationHandler                   governanceConfigurationHandler;
    private MetadataElementHandler<OpenMetadataElement>      metadataElementHandler;
    private GovernanceActionHandler<GovernanceActionElement> governanceActionHandler;

    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that GovernanceEngine is allowed to serve Assets from.
     * @param defaultZones list of zones that GovernanceEngine should set in all new Assets.
     * @param publishedZones list of zones that governance engine can use to make a governance service visible.
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize max number of results to return on single request.
     * @param outTopicEventBusConnection inner event bus connection to use to build topic connection to send to client if they which
     *                                   to listen on the out topic.
     *
     * @throws NewInstanceException a problem occurred during initialization
     */
    public GovernanceEngineInstance(OMRSRepositoryConnector repositoryConnector,
                                    List<String>            supportedZones,
                                    List<String>            defaultZones,
                                    List<String>            publishedZones,
                                    AuditLog                auditLog,
                                    String                  localServerUserId,
                                    int                     maxPageSize,
                                    Connection              outTopicEventBusConnection) throws NewInstanceException
    {
        super(myDescription.getAccessServiceFullName(),
              repositoryConnector,
              supportedZones,
              defaultZones,
              publishedZones,
              auditLog,
              localServerUserId,
              maxPageSize,
              null,
              null,
              GovernanceEngineOutTopicClientProvider.class.getName(),
              outTopicEventBusConnection);

        final String methodName = "new ServiceInstance";

        if (repositoryHandler != null)
        {
            this.governanceConfigurationHandler = new GovernanceConfigurationHandler(serviceName,
                                                                                     serverName,
                                                                                     invalidParameterHandler,
                                                                                     repositoryHandler,
                                                                                     repositoryHelper,
                                                                                     localServerUserId,
                                                                                     securityVerifier,
                                                                                     supportedZones,
                                                                                     defaultZones,
                                                                                     publishZones,
                                                                                     auditLog);

            this.metadataElementHandler = new MetadataElementHandler<>(new MetadataElementConverter<>(repositoryHelper, serviceName, serverName),
                                                                       OpenMetadataElement.class,
                                                                       serviceName,
                                                                       serverName,
                                                                       invalidParameterHandler,
                                                                       repositoryHandler,
                                                                       repositoryHelper,
                                                                       localServerUserId,
                                                                       securityVerifier,
                                                                       supportedZones,
                                                                       defaultZones,
                                                                       publishZones,
                                                                       auditLog);

            this.governanceActionHandler = new GovernanceActionHandler<>(new GovernanceActionConverter<>(repositoryHelper, serviceName, serverName),
                                                                         GovernanceActionElement.class,
                                                                         serviceName,
                                                                         serverName,
                                                                         invalidParameterHandler,
                                                                         repositoryHandler,
                                                                         repositoryHelper,
                                                                         localServerUserId,
                                                                         securityVerifier,
                                                                         supportedZones,
                                                                         defaultZones,
                                                                         publishZones,
                                                                         auditLog);
        }
        else
        {
            throw new NewInstanceException(GovernanceEngineErrorCode.OMRS_NOT_INITIALIZED.getMessageDefinition(methodName),
                                           this.getClass().getName(),
                                           methodName);
        }
    }


    /**
     * Return the handler for configuration requests.
     *
     * @return handler object
     */
    GovernanceConfigurationHandler getGovernanceConfigurationHandler()
    {
        return governanceConfigurationHandler;
    }


    /**
     * Return the handler for open metadata store requests.
     *
     * @return handler object
     */
    public MetadataElementHandler<OpenMetadataElement> getMetadataElementHandler()
    {
        return metadataElementHandler;
    }


    /**
     * Return the handler for governance action requests.
     *
     * @return handler object
     */
    public GovernanceActionHandler<GovernanceActionElement> getGovernanceActionHandler()
    {
        return governanceActionHandler;
    }
}
