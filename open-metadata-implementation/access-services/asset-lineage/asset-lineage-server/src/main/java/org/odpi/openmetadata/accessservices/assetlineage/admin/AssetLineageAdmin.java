/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.assetlineage.admin;


import org.odpi.openmetadata.accessservices.assetlineage.auditlog.AssetLineageAuditCode;
import org.odpi.openmetadata.accessservices.assetlineage.listeners.EnterpriseTopicListener;
import org.odpi.openmetadata.accessservices.assetlineage.server.AssetLineageServicesInstance;
import org.odpi.openmetadata.adminservices.configuration.properties.AccessServiceConfig;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceAdmin;
import org.odpi.openmetadata.adminservices.ffdc.exception.OMAGConfigurationErrorException;
import org.odpi.openmetadata.frameworks.connectors.ConnectorBroker;
import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.odpi.openmetadata.repositoryservices.auditlog.OMRSAuditLog;
import org.odpi.openmetadata.repositoryservices.auditlog.OMRSAuditingComponent;
import org.odpi.openmetadata.repositoryservices.connectors.omrstopic.OMRSTopicConnector;
import org.odpi.openmetadata.repositoryservices.connectors.openmetadatatopic.OpenMetadataTopicConnector;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryConnector;
import org.odpi.openmetadata.repositoryservices.ffdc.OMRSErrorCode;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.OMRSConfigErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetLineageAdmin extends AccessServiceAdmin
{

    private static final Logger log = LoggerFactory.getLogger(AssetLineageAdmin.class);
    private OMRSAuditLog auditLog;
    private String serverName;
    private AssetLineageServicesInstance instance;


    /**
     * Initialize the access service.
     *
     * @param accessServiceConfig specific configuration properties for this access service.
     * @param enterpriseOMRSTopicConnector         connector for receiving OMRS Events from the cohorts
     * @param repositoryConnector                  connector for querying the cohort repositories
     * @param auditLog                             audit log component for logging messages.
     * @param serverUserName                       user id to use on OMRS calls where there is no end user.
     * @throws OMAGConfigurationErrorException invalid parameters in the configuration properties.
     */
    @Override
    public void initialize(AccessServiceConfig accessServiceConfig,
                           OMRSTopicConnector enterpriseOMRSTopicConnector,
                           OMRSRepositoryConnector repositoryConnector,
                           OMRSAuditLog auditLog,
                           String serverUserName) throws OMAGConfigurationErrorException
    {
        final String actionDescription = "initialize";
        AssetLineageAuditCode auditCode;

        try {
            auditCode = AssetLineageAuditCode.SERVICE_INITIALIZING;
            auditLog.logRecord(actionDescription,
                    auditCode.getLogMessageId(),
                    auditCode.getSeverity(),
                    auditCode.getFormattedLogMessage(),
                    null,
                    auditCode.getSystemAction(),
                    auditCode.getUserAction());

            this.auditLog = auditLog;

            if (repositoryConnector != null) {
                serverName = repositoryConnector.getServerName();
            }

            Connection outTopicConnection = accessServiceConfig.getAccessServiceOutTopic();
            String outTopicName = getTopicName(outTopicConnection);

            OpenMetadataTopicConnector assetLineageOutTopicConnector = initializeAssetLineageTopicConnector(accessServiceConfig.getAccessServiceOutTopic());

            if (enterpriseOMRSTopicConnector != null) {
                auditCode = AssetLineageAuditCode.SERVICE_REGISTERED_WITH_ENTERPRISE_TOPIC;
                auditLog.logRecord(actionDescription,
                        auditCode.getLogMessageId(),
                        auditCode.getSeverity(),
                        auditCode.getFormattedLogMessage(serverName),
                        null,
                        auditCode.getSystemAction(),
                        auditCode.getUserAction());

                EnterpriseTopicListener enterpriseTopicListener = new EnterpriseTopicListener(accessServiceConfig.getAccessServiceOutTopic(),
                        repositoryConnector.getRepositoryHelper(),
                        repositoryConnector.getRepositoryValidator(),
                        accessServiceConfig.getAccessServiceName(),
                        null,
                        auditLog);
                enterpriseOMRSTopicConnector.registerListener(enterpriseTopicListener);
            }


            if (assetLineageOutTopicConnector != null) {
                startConnector(AssetLineageAuditCode.SERVICE_REGISTERED_WITH_AL_OUT_TOPIC, actionDescription, outTopicName, assetLineageOutTopicConnector);
            }

            this.instance = new AssetLineageServicesInstance(repositoryConnector, null, auditLog);
            auditCode = AssetLineageAuditCode.SERVICE_INITIALIZED;
            auditLog.logRecord(actionDescription,
                    auditCode.getLogMessageId(),
                    auditCode.getSeverity(),
                    auditCode.getFormattedLogMessage(serverName),
                    null,
                    auditCode.getSystemAction(),
                    auditCode.getUserAction());
        } catch (OMAGConfigurationErrorException error) {
            throw error;
        } catch (Exception error) {
            auditCode = AssetLineageAuditCode.SERVICE_INSTANCE_FAILURE;
            auditLog.logRecord(actionDescription,
                    auditCode.getLogMessageId(),
                    auditCode.getSeverity(),
                    auditCode.getFormattedLogMessage(error.getMessage()),
                    null,
                    auditCode.getSystemAction(),
                    auditCode.getUserAction());
        }
    }

    private void startConnector(AssetLineageAuditCode auditCode, String actionDescription, String topicName, OpenMetadataTopicConnector topicConnector) throws OMAGConfigurationErrorException {

        auditLog.logRecord(actionDescription,
                auditCode.getLogMessageId(),
                auditCode.getSeverity(),
                auditCode.getFormattedLogMessage(topicName),
                null,
                auditCode.getSystemAction(),
                auditCode.getUserAction());

        try {
            topicConnector.start();
        } catch (ConnectorCheckedException e) {
            auditCode = AssetLineageAuditCode.ERROR_INITIALIZING_ASSET_LINEAGE_TOPIC_CONNECTION;
            auditLog.logRecord(actionDescription,
                    auditCode.getLogMessageId(),
                    auditCode.getSeverity(),
                    auditCode.getFormattedLogMessage(topicName, serverName),
                    null,
                    auditCode.getSystemAction(),
                    auditCode.getUserAction());
            throw new OMAGConfigurationErrorException(400,
                    AssetLineageAdmin.class.getSimpleName(),
                    actionDescription,
                    auditCode.getFormattedLogMessage(),
                    auditCode.getSystemAction(),
                    auditCode.getUserAction()
            );
        }
    }


    /**
     * Returns the topic created based on connection properties
     *
     * @param topicConnection properties of the topic
     * @return the topic created based on the connection properties
     */
    private OpenMetadataTopicConnector initializeAssetLineageTopicConnector(Connection topicConnection) {
        final String actionDescription = "initialize";
        if (topicConnection != null) {
            try {
                return getTopicConnector(topicConnection);
            } catch (Exception e) {
                AssetLineageAuditCode auditCode = AssetLineageAuditCode.ERROR_INITIALIZING_CONNECTION;
                auditLog.logRecord(actionDescription,
                        auditCode.getLogMessageId(),
                        auditCode.getSeverity(),
                        auditCode.getFormattedLogMessage(topicConnection.toString(), serverName, e.getMessage()),
                        null,
                        auditCode.getSystemAction(),
                        auditCode.getUserAction());
                throw e;
            }

        }
        return null;

    }

    /**
     * Returns the connector created from topic connection properties
     *
     * @param topicConnection properties of the topic connection
     * @return the connector created based on the topic connection properties
     */
    private OpenMetadataTopicConnector getTopicConnector(Connection topicConnection) {
        try {
            ConnectorBroker connectorBroker = new ConnectorBroker();

            OpenMetadataTopicConnector topicConnector = (OpenMetadataTopicConnector) connectorBroker.getConnector(topicConnection);

            topicConnector.setAuditLog(auditLog.createNewAuditLog(OMRSAuditingComponent.OPEN_METADATA_TOPIC_CONNECTOR));

            return topicConnector;
        } catch (Throwable error) {
            String methodName = "getTopicConnector";

            if (log.isDebugEnabled()) {
                log.debug("Unable to create topic connector: " + error.toString());
            }

            OMRSErrorCode errorCode = OMRSErrorCode.NULL_TOPIC_CONNECTOR;
            String errorMessage = errorCode.getErrorMessageId()
                    + errorCode.getFormattedErrorMessage("getTopicConnector");

            throw new OMRSConfigErrorException(errorCode.getHTTPErrorCode(),
                    this.getClass().getName(),
                    methodName,
                    errorMessage,
                    errorCode.getSystemAction(),
                    errorCode.getUserAction(),
                    error);

        }
    }

    /**
     * Shutdown the access service.
     */
    public void shutdown()
    {
        final String            actionDescription = "shutdown";
        AssetLineageAuditCode  auditCode;

        if (instance != null)
        {
            this.instance.shutdown();
        }

        auditCode = AssetLineageAuditCode.SERVICE_SHUTDOWN;
        auditLog.logRecord(actionDescription,
                auditCode.getLogMessageId(),
                auditCode.getSeverity(),
                auditCode.getFormattedLogMessage(serverName),
                null,
                auditCode.getSystemAction(),
                auditCode.getUserAction());
    }
}
