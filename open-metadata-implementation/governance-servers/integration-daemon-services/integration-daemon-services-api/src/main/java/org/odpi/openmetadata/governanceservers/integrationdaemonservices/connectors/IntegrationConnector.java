/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.governanceservers.integrationdaemonservices.connectors;

import org.odpi.openmetadata.frameworks.connectors.ffdc.ConnectorCheckedException;

/**
 * IntegrationConnector is the abstract interface for a connector that is managed by the integration services.
 * If the connector throws an exception from any of these methods, it is flagged with a failed status
 * and will not be called again until it is restarted.
 */
public interface IntegrationConnector
{
    /**
     * Indicates that the connector is completely configured and can begin processing.
     * This call can be used to register with non-blocking services.
     *
     * @throws ConnectorCheckedException there is a problem within the connector.
     */
    void start() throws ConnectorCheckedException;


    /**
     * This method is for blocking calls to wait for new metadata.  It is called from its own thread iff
     * the connector is configured to have its own thread.  It is recommended that the engage() method
     * returns when each blocking call completes.  The integration daemon will pause a second and then
     * call engage() again.  This pattern enables the calling thread to detect the shutdown of the integration
     * daemon.
     *
     * @throws ConnectorCheckedException there is a problem within the connector.
     */
    void engage() throws ConnectorCheckedException;


    /**
     * Requests that the connector does a comparison of the metadata in the third party technology and open metadata repositories.
     * Refresh is called when the integration connector first starts and then at intervals defined in the connector's configuration
     * as well as any external REST API calls to explicitly refresh the connector.
     *
     * @throws ConnectorCheckedException there is a problem with the connector.  It is not able to refresh the metadata.
     */
    void refresh() throws ConnectorCheckedException;


    /**
     * Free up any resources held since the connector is no longer needed.
     *
     * @throws ConnectorCheckedException there is a problem within the connector.
     */
    void disconnect() throws ConnectorCheckedException;
}
