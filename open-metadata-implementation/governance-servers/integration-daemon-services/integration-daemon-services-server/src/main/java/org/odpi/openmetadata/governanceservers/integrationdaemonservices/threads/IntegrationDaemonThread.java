/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.governanceservers.integrationdaemonservices.threads;

import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.ffdc.IntegrationDaemonServicesAuditCode;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.handlers.IntegrationConnectorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * IntegrationDaemonThread is the class responsible for managing executing integration connectors
 * within an integration daemon.  It manages the automated refresh of the connectors.
 * The connectors are also being refreshed through the REST API.
 */
public class IntegrationDaemonThread implements Runnable
{
    private static final Logger log = LoggerFactory.getLogger(IntegrationDaemonThread.class);

    private String                            integrationDaemonName;
    private List<IntegrationConnectorHandler> connectorHandlers;
    private AuditLog                          auditLog;


    private final AtomicBoolean running = new AtomicBoolean(false);


    /**
     * Constructor provides access to the variables needed to run the connector.
     *
     * @param integrationDaemonName name of this integration daemon server
     * @param connectorHandlers wrapper for the connector.
     * @param auditLog logging destination
     */
    public IntegrationDaemonThread(String                            integrationDaemonName,
                                   List<IntegrationConnectorHandler> connectorHandlers,
                                   AuditLog                          auditLog)
    {
        this.integrationDaemonName = integrationDaemonName;
        this.connectorHandlers     = connectorHandlers;
        this.auditLog              = auditLog;
    }


    /**
     * Requests that the integration daemon thread starts
     */
    public void start()
    {
        final String threadName = "::IntegrationDaemonThread";

        Thread worker = new Thread(this, integrationDaemonName + threadName);
        worker.start();
    }


    /**
     * Requests that the integration daemon thread shuts down.
     */
    public void stop()
    {
        running.set(false);
    }


    /**
     * This is the method that runs in the new thread when it is started.
     */
    @Override
    public void run()
    {
        final String actionDescription = "Periodic refresh of connector";

        running.set(true);

        auditLog.logMessage(actionDescription,
                            IntegrationDaemonServicesAuditCode.DAEMON_THREAD_STARTING.getMessageDefinition(integrationDaemonName));

        while (running.get())
        {
            Date now = new Date();

            for (IntegrationConnectorHandler connectorHandler : connectorHandlers)
            {
                if (connectorHandler != null)
                {
                    if (connectorHandler.getLastRefreshTime() == null)
                    {
                        auditLog.logMessage(actionDescription,
                                            IntegrationDaemonServicesAuditCode.DAEMON_CONNECTOR_FIRST_REFRESH.
                                                    getMessageDefinition(connectorHandler.getIntegrationConnectorName(),
                                                                         integrationDaemonName));

                        connectorHandler.refreshConnector(actionDescription);
                    }
                    else if (connectorHandler.getMinSecondsBetweenRefresh() > 0)
                    {
                        long nextRefreshTime =
                                connectorHandler.getLastRefreshTime().getTime() +
                                        (connectorHandler.getMinSecondsBetweenRefresh() * 1000);

                        if (nextRefreshTime < now.getTime())
                        {
                            auditLog.logMessage(actionDescription,
                                                IntegrationDaemonServicesAuditCode.DAEMON_CONNECTOR_REFRESH.
                                                        getMessageDefinition(connectorHandler.getIntegrationConnectorName(),
                                                                             integrationDaemonName));

                            connectorHandler.refreshConnector(actionDescription);
                        }
                    }
                }
            }

            waitToRetry();
        }

        auditLog.logMessage(actionDescription,
                            IntegrationDaemonServicesAuditCode.DAEMON_THREAD_TERMINATING.getMessageDefinition(integrationDaemonName));

    }


    /**
     * Wait before retrying ...
     */
    private void waitToRetry()
    {
        final int  sleepTime = 1000;

        try
        {
            Thread.sleep(sleepTime);
        }
        catch (Exception error)
        {
            log.error("Ignored exception from sleep - probably ok", error);
        }
    }
}
