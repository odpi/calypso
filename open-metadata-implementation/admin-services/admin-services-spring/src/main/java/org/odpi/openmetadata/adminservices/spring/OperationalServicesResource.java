/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adminservices.spring;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.odpi.openmetadata.adminservices.OMAGServerOperationalServices;
import org.odpi.openmetadata.adminservices.configuration.properties.OMAGServerConfig;
import org.odpi.openmetadata.adminservices.rest.OMAGServerConfigResponse;
import org.odpi.openmetadata.adminservices.rest.SuccessMessageResponse;
import org.odpi.openmetadata.commonservices.ffdc.rest.VoidResponse;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.springframework.web.bind.annotation.*;

/**
 * OperationalServicesResource provides the REST API for controlling the start up, management and
 * shutdown of services in the OMAG Server.
 */
@RestController
@RequestMapping("/open-metadata/admin-services/users/{userId}/servers/{serverName}")

@Tag(name="Administration Services - Operational",
        description="The operational administration services support the management" +
                "of OMAG Server instances.  This includes starting and stopping the servers as well as querying and changing their operational state.",
        externalDocs=@ExternalDocumentation(description="Further information",
                url="https://egeria.odpi.org/open-metadata-implementation/admin-services/docs/user/operating-omag-server.html"))


public class OperationalServicesResource
{
    private OMAGServerOperationalServices operationalServices = new OMAGServerOperationalServices();

    /*
     * ========================================================================================
     * Activate and deactivate the open metadata and governance capabilities in the OMAG Server
     */

    /**
     * Activate the Open Metadata and Governance (OMAG) server using the configuration document stored for this server.
     *
     * @param userId  user that is issuing the request
     * @param serverName  local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @PostMapping(path = "/instance")

    @Operation(summary="Activate server with stored configuration document",
               description="Activate the named OMAG server using the appropriate configuration document found in the configuration store.",
            externalDocs=@ExternalDocumentation(description="Configuration Documents",
                    url="https://egeria.odpi.org/open-metadata-implementation/admin-services/docs/concepts/configuration-document.html"))

    public SuccessMessageResponse activateWithStoredConfig(@PathVariable String userId,
                                                           @PathVariable String serverName)
    {
        return operationalServices.activateWithStoredConfig(userId, serverName);
    }


    /**
     * Activate the open metadata and governance services using the supplied configuration
     * document.
     *
     * @param userId  user that is issuing the request
     * @param configuration  properties used to initialize the services
     * @param serverName  local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @PostMapping(path = "/instance/configuration")

    @Operation(summary="Activate server with supplied configuration document",
            description="Activate the named OMAG server using the supplied configuration document. This configuration " +
                    "document is added to the configuration store, over-writing any previous configuration for this server.",
            externalDocs=@ExternalDocumentation(description="Configuration Documents",
                    url="https://egeria.odpi.org/open-metadata-implementation/admin-services/docs/concepts/configuration-document.html"))

    public SuccessMessageResponse activateWithSuppliedConfig(@PathVariable String           userId,
                                                             @PathVariable String           serverName,
                                                             @RequestBody  OMAGServerConfig configuration)
    {
        return operationalServices.activateWithSuppliedConfig(userId, serverName, configuration);
    }


    /**
     * Temporarily deactivate any open metadata and governance services.
     *
     * @param userId  user that is issuing the request
     * @param serverName  local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the serverName is invalid.
     */
    @DeleteMapping(path = "/instance")

    @Operation(summary="Shutdown server",
            description="Temporarily shutdown the named OMAG server.  This server can be restarted as a later time.")

    public VoidResponse deactivateTemporarily(@PathVariable String  userId,
                                              @PathVariable String  serverName)
    {
        return operationalServices.deactivateTemporarily(userId, serverName);
    }


    /**
     * Permanently deactivate any open metadata and governance services and unregister from
     * any cohorts.
     *
     * @param userId  user that is issuing the request
     * @param serverName  local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the serverName is invalid.
     */
    @DeleteMapping(path = "")

    @Operation(summary="Shutdown and delete server",
            description="Permanently shutdown the named OMAG server and delete its configuration.  The server will also be removed " +
                    "from any open metadata repository cohorts it has registered with.")

    public VoidResponse deactivatePermanently(@PathVariable String  userId,
                                              @PathVariable String  serverName)
    {
        return operationalServices.deactivatePermanently(userId, serverName);
    }


    /*
     * =============================================================
     * Operational status and control
     */

    /**
     * Return the configuration used for the current active instance of the server.  Null is returned if
     * the server instance is not running.
     *
     * @param userId  user that is issuing the request
     * @param serverName  local server name
     * @return configuration properties used to initialize the server or null if not running or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @GetMapping(path = "/instance/configuration")

    @Operation(summary="Retrieve active server's running configuration",
            description="Retrieve the configuration document used to start a running instance of a server.  The stored configuration" +
                    "document may have changed since the server was started.  This operation makes it possible to verify the " +
                    "configuration values actually being used in the running server. \n" +
                    "\n" +
                    "Null is returned if the server is not running.",
            externalDocs=@ExternalDocumentation(description="Configuration Documents",
                    url="https://egeria.odpi.org/open-metadata-implementation/admin-services/docs/concepts/configuration-document.html"))

    public OMAGServerConfigResponse getActiveConfiguration(@PathVariable String           userId,
                                                           @PathVariable String           serverName)
    {
        return operationalServices.getActiveConfiguration(userId, serverName);
    }


    /**
     * Add a new open metadata archive to running repository.
     *
     * @param userId  user that is issuing the request.
     * @param serverName  local server name.
     * @param fileName name of the open metadata archive file.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or fileName parameter.
     */
    @PostMapping(path = "/instance/open-metadata-archives/file")

    @Operation(summary="Load open metadata archive",
            description="An open metadata archive contains metadata types and instances.  This operation loads an open metadata " +
                    "archive into an OMAG server.  It will only be successful if the server has a local repository defined.",
            externalDocs=@ExternalDocumentation(description="Open Metadata Archives",
                    url="https://egeria.odpi.org/open-metadata-resources/open-metadata-archives/index.html"))

    public VoidResponse addOpenMetadataArchiveFile(@PathVariable String userId,
                                                   @PathVariable String serverName,
                                                   @RequestBody  String fileName)
    {
        return operationalServices.addOpenMetadataArchiveFile(userId, serverName, fileName);
    }


    /**
     * Add a new open metadata archive to running repository.
     *
     * @param userId  user that is issuing the request.
     * @param serverName  local server name.
     * @param connection connection for the open metadata archive.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or connection parameter.
     */
    @PostMapping(path = "/instance/open-metadata-archives/connection")

    @Operation(summary="Load open metadata archive",
            description="An open metadata archive contains metadata types and instances.  This operation loads an open metadata " +
                    "archive into an OMAG server.  It will only be successful if the server has a local repository defined.",
            externalDocs=@ExternalDocumentation(description="Open Metadata Archives",
                    url="https://egeria.odpi.org/open-metadata-resources/open-metadata-archives/index.html"))

    public VoidResponse addOpenMetadataArchiveFile(@PathVariable String     userId,
                                                   @PathVariable String     serverName,
                                                   @RequestBody  Connection connection)
    {
        return operationalServices.addOpenMetadataArchiveFile(userId, serverName, connection);
    }
}
