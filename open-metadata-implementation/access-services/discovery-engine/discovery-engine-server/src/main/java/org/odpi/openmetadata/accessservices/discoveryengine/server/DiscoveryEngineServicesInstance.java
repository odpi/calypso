/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.discoveryengine.server;


import org.odpi.openmetadata.accessservices.discoveryengine.ffdc.DiscoveryEngineErrorCode;
import org.odpi.openmetadata.accessservices.discoveryengine.handlers.DiscoveryConfigurationHandler;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.commonservices.multitenant.OMASServiceInstance;
import org.odpi.openmetadata.commonservices.generichandlers.*;
import org.odpi.openmetadata.commonservices.multitenant.ffdc.exceptions.NewInstanceException;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.odpi.openmetadata.frameworks.discovery.properties.Annotation;
import org.odpi.openmetadata.frameworks.discovery.properties.DataField;
import org.odpi.openmetadata.frameworks.discovery.properties.DiscoveryAnalysisReport;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;

import java.util.List;

/**
 * DiscoveryEngineServicesInstance caches references to OMRS objects for a specific server.
 * It is also responsible for registering itself in the instance map.
 * It is created by the admin class during server start up and
 */
public class DiscoveryEngineServicesInstance extends OMASServiceInstance
{
    private static AccessServiceDescription myDescription = AccessServiceDescription.DISCOVERY_ENGINE_OMAS;

    private AssetHandler<OpenMetadataAPIDummyBean>                  assetHandler;
    private AnnotationHandler<Annotation>                           annotationHandler;
    private DataFieldHandler<DataField>                             dataFieldHandler;
    private DiscoveryAnalysisReportHandler<DiscoveryAnalysisReport> discoveryAnalysisReportHandler;
    private DiscoveryConfigurationHandler                           discoveryConfigurationHandler;
    private Connection                                              outTopicConnection;


    /**
     * Set up the local repository connector that will service the REST Calls.
     *
     * @param repositoryConnector link to the repository responsible for servicing the REST calls.
     * @param supportedZones list of zones that DiscoveryEngine is allowed to serve Assets from.
     * @param defaultZones list of zones that DiscoveryEngine should set in all new Assets.
     * @param defaultZones list of zones that discovery engine can use to make a discovery service visible.
     * @param auditLog logging destination
     * @param localServerUserId userId used for server initiated actions
     * @param maxPageSize max number of results to return on single request.
     * @param outTopicConnection connection to send to client if they which to listen on the out topic.
     *
     * @throws NewInstanceException a problem occurred during initialization
     */
    public DiscoveryEngineServicesInstance(OMRSRepositoryConnector repositoryConnector,
                                           List<String>            supportedZones,
                                           List<String>            defaultZones,
                                           List<String>            publishedZones,
                                           AuditLog                auditLog,
                                           String                  localServerUserId,
                                           int                     maxPageSize,
                                           Connection              outTopicConnection) throws NewInstanceException
    {
        super(myDescription.getAccessServiceFullName(),
              repositoryConnector,
              supportedZones,
              defaultZones,
              publishedZones,
              auditLog,
              localServerUserId,
              maxPageSize);

        final String methodName = "new ServiceInstance";

        this.outTopicConnection = outTopicConnection;

        if (repositoryHandler != null)
        {
            this.discoveryConfigurationHandler = new DiscoveryConfigurationHandler(serviceName,
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
            throw new NewInstanceException(DiscoveryEngineErrorCode.OMRS_NOT_INITIALIZED.getMessageDefinition(methodName),
                                           this.getClass().getName(),
                                           methodName);
        }
    }


    /**
     * Return the connection used in the client to create a connector to access events from the out topic.
     *
     * @return connection object for client
     */
    Connection getOutTopicConnection() { return outTopicConnection; }


    /**
     * Return the handler for configuration requests.
     *
     * @return handler object
     */
    DiscoveryConfigurationHandler getDiscoveryConfigurationHandler()
    {
        return discoveryConfigurationHandler;
    }


    /**
     * Return the handler for managing assets.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    AssetHandler<OpenMetadataAPIDummyBean> getAssetHandler() throws PropertyServerException
    {
        final String methodName = "getAssetHandler";

        validateActiveRepository(methodName);

        return assetHandler;
    }


    /**
     * Return the handler for managing discovery analysis report objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    DiscoveryAnalysisReportHandler<DiscoveryAnalysisReport> getDiscoveryAnalysisReportHandler() throws PropertyServerException
    {
        final String methodName = "getDiscoveryAnalysisReportHandler";

        validateActiveRepository(methodName);

        return discoveryAnalysisReportHandler;
    }


    /**
     * Return the handler for managing annotation objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    AnnotationHandler<Annotation> getAnnotationHandler() throws PropertyServerException
    {
        final String methodName = "getAnnotationHandler";

        validateActiveRepository(methodName);

        return annotationHandler;
    }


    /**
     * Return the handler for managing data field objects.
     *
     * @return  handler object
     * @throws PropertyServerException the instance has not been initialized successfully
     */
    DataFieldHandler<DataField> getDataFieldHandler() throws PropertyServerException
    {
        final String methodName = "getDataFieldHandler";

        validateActiveRepository(methodName);

        return dataFieldHandler;
    }

}
