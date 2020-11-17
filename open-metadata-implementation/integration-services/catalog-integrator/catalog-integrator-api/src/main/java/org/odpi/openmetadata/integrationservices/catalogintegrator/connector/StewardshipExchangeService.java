/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.integrationservices.catalogintegrator.connector;

import org.odpi.openmetadata.accessservices.assetmanager.client.StewardshipExchangeClient;
import org.odpi.openmetadata.accessservices.assetmanager.properties.SynchronizationDirection;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;


/**
 * StewardshipExchangeService is the context for managing classifications and exceptions and other actions of stewards.
 */
public class StewardshipExchangeService
{
    private StewardshipExchangeClient stewardshipExchangeClient;
    private String                    userId;
    private String                    assetManagerGUID;
    private String                    assetManagerName;
    private String                    connectorName;
    private SynchronizationDirection  synchronizationDirection;
    private AuditLog                  auditLog;


    /**
     * Create a new client to exchange data asset content with open metadata.
     *
     * @param stewardshipExchangeClient client for exchange requests
     * @param synchronizationDirection direction(s) that metadata can flow
     * @param userId integration daemon's userId
     * @param assetManagerGUID unique identifier of the software server capability for the asset manager
     * @param assetManagerName unique name of the software server capability for the asset manager
     * @param connectorName name of the connector using this context
     * @param auditLog logging destination
     */
    StewardshipExchangeService(StewardshipExchangeClient stewardshipExchangeClient,
                               SynchronizationDirection  synchronizationDirection,
                               String                    userId,
                               String                    assetManagerGUID,
                               String                    assetManagerName,
                               String                    connectorName,
                               AuditLog                  auditLog)
    {
        this.stewardshipExchangeClient = stewardshipExchangeClient;
        this.synchronizationDirection  = synchronizationDirection;
        this.userId                    = userId;
        this.assetManagerGUID          = assetManagerGUID;
        this.assetManagerName          = assetManagerName;
        this.connectorName             = connectorName;
        this.auditLog                  = auditLog;
    }

}
