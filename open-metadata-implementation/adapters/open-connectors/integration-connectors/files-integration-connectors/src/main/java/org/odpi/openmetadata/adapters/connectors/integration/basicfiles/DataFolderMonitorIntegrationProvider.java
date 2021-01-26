/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.adapters.connectors.integration.basicfiles;

/**
 * DataFolderMonitorIntegrationProvider is the OCF connector provider for the Data Folder Monitor Integration Connector.
 * This is one of the basic files integration connectors.
 */
public class DataFolderMonitorIntegrationProvider extends BasicFilesMonitorIntegrationProviderBase
{
    private static final String  connectorTypeGUID = "6718d248-5e0c-4e32-9d38-187318caea70";
    private static final String  connectorTypeName = "Data Folder Monitor Integration Connector";
    private static final String  connectorTypeDescription = "Connector maintains a DataFolder asset by monitoring the file directory where it is located.";

    /**
     * Constructor used to initialize the ConnectorProviderBase with the Java class name of the specific
     * store implementation.
     */
    public DataFolderMonitorIntegrationProvider()
    {
        super(connectorTypeGUID,
              connectorTypeName,
              connectorTypeDescription,
              DataFilesMonitorIntegrationConnector.class);
    }
}
