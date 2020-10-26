/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.integrationservices.files.contextmanager;

import org.odpi.openmetadata.accessservices.datamanager.client.DataManagerEventClient;
import org.odpi.openmetadata.accessservices.datamanager.client.FilesAndFoldersClient;
import org.odpi.openmetadata.accessservices.datamanager.client.MetadataSourceClient;
import org.odpi.openmetadata.accessservices.datamanager.client.rest.DataManagerRESTClient;
import org.odpi.openmetadata.accessservices.datamanager.properties.FileSystemProperties;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.connectors.IntegrationConnector;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.contextmanager.IntegrationContextManager;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.registration.IntegrationServiceDescription;
import org.odpi.openmetadata.integrationservices.files.connector.FilesIntegratorConnector;
import org.odpi.openmetadata.integrationservices.files.connector.FilesIntegratorContext;
import org.odpi.openmetadata.integrationservices.files.ffdc.FilesIntegratorErrorCode;

/**
 * FilesIntegratorContextManager provides the bridge between the integration daemon services and
 * the specific implementation of the Files Integrator integration service.
 */
public class FilesIntegratorContextManager extends IntegrationContextManager
{
    private FilesAndFoldersClient  filesAndFoldersClient  = null;
    private DataManagerEventClient dataManagerEventClient = null;
    private MetadataSourceClient   metadataSourceClient   = null;

    private String   metadataSourceQualifiedName = null;
    private String   metadataSourceGUID          = null;

    /**
     * Default constructor
     */
    public FilesIntegratorContextManager()
    {
    }


    /**
     * Suggestion for subclass to create client(s) to partner OMAS.
     *
     * @throws InvalidParameterException the subclass is not able to create one of its clients
     */
    public void createClients() throws InvalidParameterException
    {
        DataManagerRESTClient restClient;

        if (localServerPassword == null)
        {
            restClient = new DataManagerRESTClient(partnerOMASServerName,
                                                   partnerOMASPlatformRootURL,
                                                   auditLog);
        }
        else
        {
            restClient = new DataManagerRESTClient(partnerOMASServerName,
                                                   partnerOMASPlatformRootURL,
                                                   localServerUserId,
                                                   localServerPassword,
                                                   auditLog);
        }

        filesAndFoldersClient = new FilesAndFoldersClient(partnerOMASServerName,
                                                          partnerOMASPlatformRootURL,
                                                          restClient,
                                                          maxPageSize,
                                                          auditLog);

        dataManagerEventClient = new DataManagerEventClient(partnerOMASServerName,
                                                            partnerOMASPlatformRootURL,
                                                            restClient,
                                                            maxPageSize,
                                                            auditLog);

        metadataSourceClient = new MetadataSourceClient(partnerOMASServerName,
                                                        partnerOMASPlatformRootURL,
                                                        restClient,
                                                        maxPageSize,
                                                        auditLog);
    }


    /**
     * Retrieve the metadata source's unique identifier (GUID) or if it is not defined, create the software server capability
     * for this integrator.
     *
     * @param metadataSourceQualifiedName unique name of the software server capability that represents this integration
     *                                service
     * @throws InvalidParameterException one of the parameters passed (probably on initialize) is invalid
     * @throws UserNotAuthorizedException the integration daemon's userId does not have access to the partner OMAS
     * @throws PropertyServerException there is a problem in the remote server running the partner OMAS
     */
    private void setUpMetadataSource(String   metadataSourceQualifiedName) throws InvalidParameterException,
                                                                                  UserNotAuthorizedException,
                                                                                  PropertyServerException
    {
        if (metadataSourceQualifiedName != null)
        {
            this.metadataSourceQualifiedName = metadataSourceQualifiedName;
        }
        else
        {
            this.metadataSourceQualifiedName = IntegrationServiceDescription.FILES_INTEGRATOR_OMIS.getIntegrationServiceFullName();
        }

        this.metadataSourceGUID = metadataSourceClient.getMetadataSourceGUID(localServerUserId, metadataSourceQualifiedName);

        if (this.metadataSourceGUID == null)
        {
            FileSystemProperties properties = new FileSystemProperties();

            properties.setQualifiedName(metadataSourceQualifiedName);
            properties.setDisplayName(IntegrationServiceDescription.FILES_INTEGRATOR_OMIS.getIntegrationServiceFullName());
            properties.setDescription(IntegrationServiceDescription.FILES_INTEGRATOR_OMIS.getIntegrationServiceDescription());

            this.metadataSourceGUID = metadataSourceClient.createFileSystem(localServerUserId, null, null, properties);
        }
    }


    /**
     * Set up the context in the supplied connector. This is called between initialize() and start() on the connector.
     *
     * @param connectorName name of the connector
     * @param integrationConnector connector created from connection integration service configuration
     * @throws InvalidParameterException the connector is not of the correct type
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException problem accessing the property server
     */
    public void setContext(String               connectorName,
                           String               metadataSourceQualifiedName,
                           IntegrationConnector integrationConnector) throws InvalidParameterException,
                                                                             UserNotAuthorizedException,
                                                                             PropertyServerException
    {
        if (integrationConnector instanceof FilesIntegratorConnector)
        {
            FilesIntegratorConnector serviceSpecificConnector = (FilesIntegratorConnector)integrationConnector;

            this.setUpMetadataSource(metadataSourceQualifiedName);

            serviceSpecificConnector.setContext(new FilesIntegratorContext(filesAndFoldersClient,
                                                                           dataManagerEventClient,
                                                                           localServerUserId,
                                                                           metadataSourceGUID,
                                                                           metadataSourceQualifiedName));
        }
        else
        {
            final String  parameterName = "integrationConnector";
            final String  methodName = "setContext";

            throw new InvalidParameterException(FilesIntegratorErrorCode.INVALID_CONNECTOR.
                    getMessageDefinition(connectorName,
                                         IntegrationServiceDescription.FILES_INTEGRATOR_OMIS.getIntegrationServiceFullName(),
                                         FilesIntegratorConnector.class.getCanonicalName()),
                                                this.getClass().getName(),
                                                methodName,
                                                parameterName);
        }
    }
}
