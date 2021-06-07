/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.securitymanager.server.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.accessservices.securitymanager.rest.DatabaseManagerRequestBody;
import org.odpi.openmetadata.accessservices.securitymanager.rest.FileManagerRequestBody;
import org.odpi.openmetadata.accessservices.securitymanager.rest.FileSystemRequestBody;
import org.odpi.openmetadata.accessservices.securitymanager.server.SecurityManagerRESTServices;
import org.odpi.openmetadata.commonservices.ffdc.rest.ConnectionResponse;
import org.odpi.openmetadata.commonservices.ffdc.rest.GUIDResponse;
import org.springframework.web.bind.annotation.*;

/**
 * Server-side REST API support for security manager independent REST endpoints
 */
@RestController
@RequestMapping("/servers/{serverName}/open-metadata/access-services/security-manager/users/{userId}")

@Tag(name="Security Manager OMAS",
        description="The Security Manager OMAS provides APIs for tools and applications wishing to manage metadata relating to security managers.",
        externalDocs=@ExternalDocumentation(description="Security Manager Open Metadata Access Service (OMAS)",
                url="https://egeria.odpi.org/open-metadata-implementation/access-services/security-manager"))

public class SecurityManagerOMASResource
{
    private SecurityManagerRESTServices restAPI = new SecurityManagerRESTServices();


    /**
     * Instantiates a new Security Manager OMAS resource.
     */
    public SecurityManagerOMASResource()
    {
    }


    /**
     * Return the connection object for the Security Manager OMAS's out topic.
     *
     * @param serverName name of the server to route the request to
     * @param userId identifier of calling user
     * @param callerId unique identifier of the caller
     *
     * @return connection object for the out topic or
     * InvalidParameterException one of the parameters is null or invalid or
     * UserNotAuthorizedException user not authorized to issue this request or
     * PropertyServerException problem retrieving the discovery engine definition.
     */
    @GetMapping(path = "/topics/out-topic-connection/{callerId}")

    public ConnectionResponse getOutTopicConnection(@PathVariable String serverName,
                                                    @PathVariable String userId,
                                                    @PathVariable String callerId)
    {
        return restAPI.getOutTopicConnection(serverName, userId, callerId);
    }



    /**
     * Files live on a file system.  This method creates a top level software server capability for a filesystem.
     *
     * @param serverName name of calling server
     * @param userId calling user
     * @param requestBody properties of the file system
     *
     * @return unique identifier for the file system or
     * InvalidParameterException one of the parameters is null or invalid or
     * PropertyServerException problem accessing property server or
     * UserNotAuthorizedException security access problem
     */
    @PostMapping(path = "/metadata-sources/filesystems")

    public GUIDResponse   createFileSystemInCatalog(@PathVariable String                serverName,
                                                    @PathVariable String                userId,
                                                    @RequestBody  FileSystemRequestBody requestBody)
    {
        return restAPI.createFileSystemInCatalog(serverName, userId, requestBody);
    }

    /**
     * Files can be owned by a file manager.  This method creates a top level software server capability for a file manager.
     *
     * @param serverName name of calling server
     * @param userId calling user
     * @param requestBody properties of the file manager
     *
     * @return unique identifier for the file system or
     * InvalidParameterException one of the parameters is null or invalid or
     * PropertyServerException problem accessing property server or
     * UserNotAuthorizedException security access problem
     */
    @PostMapping(path = "/metadata-sources/file-managers")

    public GUIDResponse   createFileManagerInCatalog(@PathVariable String                 serverName,
                                                     @PathVariable String                 userId,
                                                     @RequestBody  FileManagerRequestBody requestBody)
    {
        return restAPI.createFileManagerInCatalog(serverName, userId, requestBody);
    }


    /**
     * Create information about the database manager (DBMS) that manages database schemas.
     *
     * @param serverName name of the server to route the request to.
     * @param userId calling user
     * @param requestBody description of the integration daemon (specify qualified name at a minimum)
     *
     * @return unique identifier of the database manager's software server capability or
     * InvalidParameterException  the bean properties are invalid or
     * UserNotAuthorizedException user not authorized to issue this request or
     * PropertyServerException    problem accessing the property server
     */
    @PostMapping(path = "/metadata-sources/database-managers")

    public GUIDResponse createDatabaseManager(@PathVariable String                     serverName,
                                              @PathVariable String                     userId,
                                              @RequestBody  DatabaseManagerRequestBody requestBody)
    {
        return restAPI.createDatabaseManagerInCatalog(serverName, userId, requestBody);
    }


    /**
     * Retrieve the unique identifier of the software server capability representing a metadata source.
     *
     * @param serverName name of the server to route the request to.
     * @param userId calling user
     * @param qualifiedName unique name of the integration daemon
     *
     * @return unique identifier of the integration daemon's software server capability or
     * InvalidParameterException  the bean properties are invalid or
     * UserNotAuthorizedException user not authorized to issue this request or
     * PropertyServerException    problem accessing the property server
     */
    @GetMapping(path = "metadata-sources/by-name/{qualifiedName}")

    public GUIDResponse  getMetadataSourceGUID(@PathVariable String serverName,
                                               @PathVariable String userId,
                                               @PathVariable String qualifiedName)
    {
        return restAPI.getMetadataSourceGUID(serverName, userId, qualifiedName);
    }
}
