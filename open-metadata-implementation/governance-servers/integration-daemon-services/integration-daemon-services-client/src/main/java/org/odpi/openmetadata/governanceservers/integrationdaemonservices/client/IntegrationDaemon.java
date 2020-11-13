/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.governanceservers.integrationdaemonservices.client;

import org.odpi.openmetadata.commonservices.ffdc.InvalidParameterHandler;
import org.odpi.openmetadata.commonservices.ffdc.rest.NullRequestBody;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.api.IntegrationDaemonAPI;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.properties.IntegrationServiceSummary;
import org.odpi.openmetadata.governanceservers.integrationdaemonservices.rest.IntegrationDaemonStatusResponse;

import java.util.List;

/**
 * IntegrationDaemon is the client library for the Integration Daemon's REST API.  The integration daemon is an OMAG Server.
 * It runs one-to-many integration services that in turn manage one-to-many integration connectors.  Each integration service
 * focuses on a particular type of third party technology and is paired with an appropriate OMAS.
 *
 * The refresh commands are used to instruct the connectors running in the integration daemon to verify the consistency
 * of the metadata in the third party technology against the values in open metadata.  All connectors are requested
 * to refresh when the integration daemon first starts.  Then refresh is called on the schedule defined in the configuration
 * and lastly as a result of calls to this API.
 */
public class IntegrationDaemon implements IntegrationDaemonAPI
{
    private IntegrationDaemonServicesRESTClient restClient;               /* Initialized in constructor */
    private String                              serverName;
    private String                              serverPlatformRootURL;

    private InvalidParameterHandler invalidParameterHandler = new InvalidParameterHandler();
    private static NullRequestBody  nullRequestBody         = new NullRequestBody();

    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param auditLog logging destination
     *
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public IntegrationDaemon(String   serverName,
                             String   serverPlatformRootURL,
                             AuditLog auditLog) throws InvalidParameterException
    {
        this.serverName = serverName;
        this.serverPlatformRootURL = serverPlatformRootURL;

        this.restClient = new IntegrationDaemonServicesRESTClient(serverName, serverPlatformRootURL, auditLog);
    }


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public IntegrationDaemon(String serverName,
                             String serverPlatformRootURL) throws InvalidParameterException
    {
        this.serverName = serverName;
        this.serverPlatformRootURL = serverPlatformRootURL;

        this.restClient = new IntegrationDaemonServicesRESTClient(serverName, serverPlatformRootURL);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @param auditLog logging destination
     *
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public IntegrationDaemon(String   serverName,
                             String   serverPlatformRootURL,
                             String   userId,
                             String   password,
                             AuditLog auditLog) throws InvalidParameterException
    {
        this.serverName = serverName;
        this.serverPlatformRootURL = serverPlatformRootURL;

        this.restClient = new IntegrationDaemonServicesRESTClient(serverName, serverPlatformRootURL, userId, password, auditLog);
    }


    /**
     * Create a new client that passes userId and password in each HTTP request.  This is the
     * userId/password of the calling server.  The end user's userId is sent on each request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param userId caller's userId embedded in all HTTP requests
     * @param password caller's userId embedded in all HTTP requests
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public IntegrationDaemon(String serverName,
                             String serverPlatformRootURL,
                             String userId,
                             String password) throws InvalidParameterException
    {
        this.serverName = serverName;
        this.serverPlatformRootURL = serverPlatformRootURL;

        this.restClient = new IntegrationDaemonServicesRESTClient(serverName, serverPlatformRootURL, userId, password);
    }


    /**
     * Refresh all connectors running in the integration daemon, regardless of the integration service they belong to.
     *
     * @param userId calling user
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException there was a problem detected by the integration daemon
     */
    public void refreshAllServices(String userId) throws InvalidParameterException,
                                                         UserNotAuthorizedException,
                                                         PropertyServerException
    {
        final String   methodName = "refreshAllServices";
        final String   urlTemplate = "/servers/{0}/open-metadata/integration-daemon/users/{1}/refresh";

        invalidParameterHandler.validateUserId(userId, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        nullRequestBody,
                                        serverName,
                                        userId);
    }


    /**
     * Refresh the requested connectors running in the requested integration service.
     *
     * @param userId calling user
     * @param serviceURLMarker integration service identifier
     * @param connectorName optional name of the connector to target - if no connector name is specified, all
     *                      connectors managed by this integration service are refreshed.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException there was a problem detected by the integration daemon
     */
    public void refreshService(String userId,
                               String serviceURLMarker,
                               String connectorName) throws InvalidParameterException,
                                                            UserNotAuthorizedException,
                                                            PropertyServerException
    {
        final String   methodName = "refreshService";
        final String   nameParameter = "serviceURLMarker";
        final String   urlTemplate = "/servers/{0}/open-metadata/integration-daemon/users/{1}/integration-service/{2}/refresh";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(serviceURLMarker, nameParameter, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        connectorName,
                                        serverName,
                                        userId,
                                        serviceURLMarker);
    }


    /**
     * Request that the integration service shutdown and recreate its integration connectors.
     *
     * @param userId calling user
     * @param serviceURLMarker integration service identifier
     * @param connectorName optional name of the connector to target - if no connector name is specified, all
     *                      connectors managed by this integration service are restarted.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException there was a problem detected by the integration daemon
     */
    public void restartService(String userId,
                               String serviceURLMarker,
                               String connectorName) throws InvalidParameterException,
                                                            UserNotAuthorizedException,
                                                            PropertyServerException
    {
        final String   methodName = "refreshService";
        final String   nameParameter = "serviceURLMarker";
        final String   urlTemplate = "/servers/{0}/open-metadata/integration-daemon/users/{1}/integration-service/{2}/restart";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateName(serviceURLMarker, nameParameter, methodName);

        restClient.callVoidPostRESTCall(methodName,
                                        serverPlatformRootURL + urlTemplate,
                                        connectorName,
                                        serverName,
                                        userId,
                                        serviceURLMarker);
    }


    /**
     * Return a summary of each of the integration services' status.
     *
     * @param userId calling user
     *
     * @return list of statuses - on for each assigned integration services or
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException there was a problem detected by the integration daemon
     */
    public List<IntegrationServiceSummary> getIntegrationDaemonStatus(String   userId) throws InvalidParameterException,
                                                                                              UserNotAuthorizedException,
                                                                                              PropertyServerException
    {
        final String   methodName = "getIntegrationDaemonStatus";
        final String   urlTemplate = "/servers/{0}/open-metadata/integration-daemon/users/{1}/status";

        invalidParameterHandler.validateUserId(userId, methodName);

        IntegrationDaemonStatusResponse restResult = restClient.callIntegrationDaemonStatusGetRESTCall(methodName,
                                                                                        serverPlatformRootURL + urlTemplate,
                                                                                        serverName,
                                                                                        userId);

        return restResult.getIntegrationServiceSummaries();
    }
}
