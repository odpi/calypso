/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.securitymanager.client;

import org.odpi.openmetadata.accessservices.securitymanager.api.MetadataSourceInterface;
import org.odpi.openmetadata.accessservices.securitymanager.client.rest.SecurityManagerRESTClient;
import org.odpi.openmetadata.accessservices.securitymanager.properties.*;
import org.odpi.openmetadata.accessservices.securitymanager.rest.DatabaseManagerRequestBody;
import org.odpi.openmetadata.accessservices.securitymanager.rest.FileManagerRequestBody;
import org.odpi.openmetadata.accessservices.securitymanager.rest.FileSystemRequestBody;
import org.odpi.openmetadata.commonservices.ffdc.rest.GUIDResponse;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.client.ConnectedAssetClientBase;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;


/**
 * MetadataSourceClient is the client for setting up the SoftwareServerCapabilities that represent metadata sources.
 */
public class MetadataSourceClient extends ConnectedAssetClientBase implements MetadataSourceInterface
{
    private SecurityManagerRESTClient restClient;               /* Initialized in constructor */

     private final String urlTemplatePrefix = "/servers/{0}/open-metadata/access-services/security-manager/users/{1}/metadata-sources";


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param auditLog logging destination
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public MetadataSourceClient(String   serverName,
                                String   serverPlatformURLRoot,
                                AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, auditLog);

        this.restClient = new SecurityManagerRESTClient(serverName, serverPlatformURLRoot, auditLog);
    }


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public MetadataSourceClient(String serverName,
                                String serverPlatformURLRoot) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot);

        this.restClient = new SecurityManagerRESTClient(serverName, serverPlatformURLRoot);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @param auditLog logging destination
     *
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public MetadataSourceClient(String   serverName,
                                String   serverPlatformURLRoot,
                                String   userId,
                                String   password,
                                AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, auditLog);

        this.restClient = new SecurityManagerRESTClient(serverName, serverPlatformURLRoot, userId, password, auditLog);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public MetadataSourceClient(String serverName,
                                String serverPlatformURLRoot,
                                String userId,
                                String password) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot);

        this.restClient = new SecurityManagerRESTClient(serverName, serverPlatformURLRoot, userId, password);
    }


    /**
     * Create a new client that is to be used within an OMAG Server.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param restClient pre-initialized REST client
     * @param maxPageSize pre-initialized parameter limit
     * @param auditLog logging destination
     * @throws InvalidParameterException there is a problem with the information about the remote OMAS
     */
    public MetadataSourceClient(String                serverName,
                                String                serverPlatformURLRoot,
                                SecurityManagerRESTClient restClient,
                                int                   maxPageSize,
                                AuditLog              auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, maxPageSize, auditLog);
        
        this.restClient = restClient;
    }


    /* ========================================================
     * The metadata source represents the third party technology this integration processing is connecting to
     */

    /**
     * Create information about a File System that is being used to store data files.
     *
     * @param userId calling user
     * @param externalSourceGUID        guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName        name of the software server capability entity that represented the external source
     * @param fileSystemProperties description of the file system
     *
     * @return unique identifier of the file system's software server capability
     *
     * @throws InvalidParameterException  the bean properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException    problem accessing the property server
     */
    @Override
    public String  createFileSystem(String               userId,
                                    String               externalSourceGUID,
                                    String               externalSourceName,
                                    FileSystemProperties fileSystemProperties) throws InvalidParameterException,
                                                                                      UserNotAuthorizedException,
                                                                                      PropertyServerException
    {
        final String methodName                  = "createFileSystem";
        final String propertiesParameterName     = "fileSystemProperties";
        final String qualifiedNameParameterName  = "qualifiedName";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateObject(fileSystemProperties, propertiesParameterName, methodName);
        invalidParameterHandler.validateName(fileSystemProperties.getQualifiedName(), qualifiedNameParameterName, methodName);

        FileSystemRequestBody requestBody = new FileSystemRequestBody(fileSystemProperties);

        requestBody.setExternalSourceGUID(externalSourceGUID);
        requestBody.setExternalSourceName(externalSourceName);

        final String urlTemplate = serverPlatformURLRoot + urlTemplatePrefix + "/filesystems";

        GUIDResponse restResult = restClient.callGUIDPostRESTCall(methodName,
                                                                  urlTemplate,
                                                                  requestBody,
                                                                  serverName,
                                                                  userId);

        return restResult.getGUID();
    }


    /**
     * Create information about an application that manages a collection of data files.
     *
     * @param userId calling user
     * @param externalSourceGUID        guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName        name of the software server capability entity that represented the external source
     * @param fileManagerProperties description of the
     *
     * @return unique identifier of the file manager's software server capability
     *
     * @throws InvalidParameterException  the bean properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException    problem accessing the property server
     */
    @Override
    public String  createFileManager(String                userId,
                                     String                externalSourceGUID,
                                     String                externalSourceName,
                                     FileManagerProperties fileManagerProperties) throws InvalidParameterException,
                                                                                         UserNotAuthorizedException,
                                                                                         PropertyServerException
    {
        final String methodName                  = "createFileManager";
        final String propertiesParameterName     = "fileManagerProperties";
        final String qualifiedNameParameterName  = "qualifiedName";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateObject(fileManagerProperties, propertiesParameterName, methodName);
        invalidParameterHandler.validateName(fileManagerProperties.getQualifiedName(), qualifiedNameParameterName, methodName);

        FileManagerRequestBody requestBody = new FileManagerRequestBody(fileManagerProperties);

        requestBody.setExternalSourceGUID(externalSourceGUID);
        requestBody.setExternalSourceName(externalSourceName);

        final String urlTemplate = serverPlatformURLRoot + urlTemplatePrefix + "/file-managers";

        GUIDResponse restResult = restClient.callGUIDPostRESTCall(methodName,
                                                                  urlTemplate,
                                                                  requestBody,
                                                                  serverName,
                                                                  userId);

        return restResult.getGUID();
    }


    /**
     * Create information about the integration daemon that is managing the acquisition of metadata from the
     * security manager.  Typically this is Egeria's security manager integrator OMIS.
     *
     * @param userId calling user
     * @param externalSourceGUID        guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName        name of the software server capability entity that represented the external source
     * @param databaseManagerProperties description of the integration daemon (specify qualified name at a minimum)
     *
     * @return unique identifier of the integration daemon's software server capability
     *
     * @throws InvalidParameterException  the bean properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException    problem accessing the property server
     */
    @Override
    public String createDatabaseManager(String                     userId,
                                        String                     externalSourceGUID,
                                        String                     externalSourceName,
                                        DatabaseManagerProperties  databaseManagerProperties) throws InvalidParameterException,
                                                                                                     UserNotAuthorizedException,
                                                                                                     PropertyServerException
    {
        final String methodName                  = "createDatabaseManager";
        final String propertiesParameterName     = "databaseManagerProperties";
        final String qualifiedNameParameterName  = "qualifiedName";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateObject(databaseManagerProperties, propertiesParameterName, methodName);
        invalidParameterHandler.validateName(databaseManagerProperties.getQualifiedName(), qualifiedNameParameterName, methodName);

        final String urlTemplate = serverPlatformURLRoot + urlTemplatePrefix + "/database-managers";

        DatabaseManagerRequestBody requestBody = new DatabaseManagerRequestBody(databaseManagerProperties);

        requestBody.setExternalSourceGUID(externalSourceGUID);
        requestBody.setExternalSourceName(externalSourceName);

        GUIDResponse restResult = restClient.callGUIDPostRESTCall(methodName,
                                                                  urlTemplate,
                                                                  requestBody,
                                                                  serverName,
                                                                  userId);

        return restResult.getGUID();
    }


    /**
     * Retrieve the unique identifier of the integration daemon.
     *
     * @param userId calling user
     * @param qualifiedName unique name of the integration daemon
     *
     * @return unique identifier of the integration daemon's software server capability
     *
     * @throws InvalidParameterException  the bean properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException    problem accessing the property server
     */
    @Override
    public String getMetadataSourceGUID(String  userId,
                                        String  qualifiedName) throws InvalidParameterException,
                                                                      UserNotAuthorizedException,
                                                                      PropertyServerException
    {
        final String methodName                  = "getMetadataSourceGUID";
        final String qualifiedNameParameterName  = "qualifiedName";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(qualifiedName, qualifiedNameParameterName, methodName);

        final String urlTemplate = serverPlatformURLRoot + urlTemplatePrefix + "/by-name/{2}";

        GUIDResponse restResult = restClient.callGUIDGetRESTCall(methodName,
                                                                  urlTemplate,
                                                                  serverName,
                                                                  userId,
                                                                  qualifiedName);

        return restResult.getGUID();
    }
}
