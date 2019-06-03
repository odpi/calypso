/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.ocf.metadatamanagement.client;

import org.odpi.openmetadata.commonservices.ffdc.InvalidParameterHandler;
import org.odpi.openmetadata.commonservices.ffdc.RESTExceptionHandler;
import org.odpi.openmetadata.commonservices.ffdc.rest.GUIDResponse;
import org.odpi.openmetadata.commonservices.ffdc.rest.NullRequestBody;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.ffdc.OMAGOCFErrorCode;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.rest.AssetResponse;
import org.odpi.openmetadata.commonservices.ocf.metadatamanagement.rest.ConnectionResponse;
import org.odpi.openmetadata.frameworks.connectors.Connector;
import org.odpi.openmetadata.frameworks.connectors.ConnectorBroker;
import org.odpi.openmetadata.frameworks.connectors.ffdc.*;
import org.odpi.openmetadata.frameworks.connectors.properties.AssetUniverse;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Asset;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;

/**
 * ConnectedAssetClientBase provides a base calls for clients that support an OCF interface.
 * In particular, it manages the retrieval of connections for assets, and the creation of connectors.
 */
public class ConnectedAssetClientBase
{
    protected String                  serverName;               /* Initialized in constructor */
    protected String                  serverPlatformRootURL;    /* Initialized in constructor */

    protected InvalidParameterHandler invalidParameterHandler = new InvalidParameterHandler();
    protected RESTExceptionHandler    exceptionHandler        = new RESTExceptionHandler();
    protected NullRequestBody         nullRequestBody         = new NullRequestBody();


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public ConnectedAssetClientBase(String serverName,
                                    String serverPlatformRootURL) throws InvalidParameterException
    {
        final String methodName = "Client Constructor";

        invalidParameterHandler.validateOMAGServerPlatformURL(serverPlatformRootURL, serverName, methodName);

        this.serverName = serverName;
        this.serverPlatformRootURL = serverPlatformRootURL;
    }


    /**
     * Return the basic properties of a asset.
     *
     * @param restClient client that calls REST APIs
     * @param userId calling user
     * @param guid unique identifier of asset
     * @param methodName calling method
     *
     * @return Asset bean
     *
     * @throws InvalidParameterException the name is invalid
     * @throws PropertyServerException there is a problem access in the property server
     * @throws UserNotAuthorizedException the user does not have access to the properties
     */
    protected Asset getAssetSummary(OCFRESTClient  restClient,
                                    String         userId,
                                    String         guid,
                                    String         methodName) throws InvalidParameterException,
                                                                      PropertyServerException,
                                                                      UserNotAuthorizedException
    {
        final String   urlTemplate = "/servers/{0}/open-metadata/common-services/ocf/connected-asset/users/{1}/assets/{2}";

        AssetResponse restResult = restClient.callAssetGetRESTCall(methodName,
                                                                   serverPlatformRootURL + urlTemplate,
                                                                   serverName,
                                                                   userId,
                                                                   guid);

        exceptionHandler.detectAndThrowInvalidParameterException(methodName, restResult);
        exceptionHandler.detectAndThrowUserNotAuthorizedException(methodName, restResult);
        exceptionHandler.detectAndThrowPropertyServerException(methodName, restResult);

        return restResult.getAsset();
    }


    /**
     * Returns a comprehensive collection of properties about the requested asset.
     *
     * @param userId         userId of user making request.
     * @param assetGUID      unique identifier for asset.
     *
     * @return a comprehensive collection of properties about the asset.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving the asset properties from the property servers).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public AssetUniverse getAssetProperties(String userId,
                                            String assetGUID) throws InvalidParameterException,
                                                                     PropertyServerException,
                                                                     UserNotAuthorizedException
    {
        final String   methodName = "getAssetProperties";
        final String   guidParameter = "assetGUID";

        invalidParameterHandler.validateUserId(userId, methodName);
        invalidParameterHandler.validateGUID(assetGUID, guidParameter, methodName);

        try
        {
            /*
             * Make use of the ConnectedAsset OMAS Service which provides the metadata services for the
             * Open Connector Framework (OCF).
             */
            return new ConnectedAssetUniverse(serverName, serverPlatformRootURL, userId, assetGUID);
        }
        catch (UserNotAuthorizedException | InvalidParameterException | PropertyServerException error)
        {
            throw error;
        }
        catch (Throwable error)
        {
            OMAGOCFErrorCode errorCode    = OMAGOCFErrorCode.NO_ASSET_PROPERTIES;
            String           errorMessage = errorCode.getErrorMessageId() + errorCode.getFormattedErrorMessage(methodName, error.getMessage());

            throw new PropertyServerException(errorCode.getHTTPErrorCode(),
                                              this.getClass().getName(),
                                              methodName,
                                              errorMessage,
                                              errorCode.getSystemAction(),
                                              errorCode.getUserAction());
        }
    }


    /**
     * Use the Open Connector Framework (OCF) to create a connector using the supplied connection.
     *
     * @param restClient client that calls REST APIs
     * @param requestedConnection  connection describing the required connector.
     * @param methodName  name of the calling method.
     *
     * @return a new connector.
     *
     * @throws ConnectionCheckedException  there are issues with the values in the connection
     * @throws ConnectorCheckedException the connector had an operational issue accessing the asset.
     */
    protected Connector getConnectorForConnection(OCFRESTClient   restClient,
                                                  String          userId,
                                                  Connection      requestedConnection,
                                                  String          methodName) throws ConnectionCheckedException,
                                                                                     ConnectorCheckedException
    {
        ConnectorBroker connectorBroker = new ConnectorBroker();

        /*
         * Pass the connection to the ConnectorBroker to create the connector instance.
         * Again, exceptions from this process are returned directly to the caller.
         */
        Connector newConnector = connectorBroker.getConnector(requestedConnection);

        /*
         * If no exception is thrown by getConnector, we should have a connector instance.
         */
        if (newConnector == null)
        {
            /*
             * This is probably some sort of logic error since the connector should have been returned.
             * Whatever the cause, the process can not proceed without a connector.
             */
            OMAGOCFErrorCode errorCode    = OMAGOCFErrorCode.NULL_CONNECTOR_RETURNED;
            String           errorMessage = errorCode.getErrorMessageId()
                                          + errorCode.getFormattedErrorMessage(requestedConnection.getQualifiedName(),
                                                                               serverName,
                                                                               serverPlatformRootURL);

            throw new ConnectorCheckedException(errorCode.getHTTPErrorCode(),
                                                this.getClass().getName(),
                                                methodName,
                                                errorMessage,
                                                errorCode.getSystemAction(),
                                                errorCode.getUserAction(),
                                                null);
        }

        try
        {
            String  assetGUID = this.getAssetForConnection(restClient, userId, requestedConnection.getGUID());

            /*
             * If the connector is successfully created, set up the Connected Asset Properties for the connector.
             * The properties should be retrieved from the open metadata repositories, so use an OMAS implementation
             * of the ConnectedAssetProperties object.
             */
            ConnectedAssetProperties connectedAssetProperties = new ConnectedAssetProperties(serverName,
                                                                                             userId,
                                                                                             serverPlatformRootURL,
                                                                                             newConnector.getConnectorInstanceId(),
                                                                                             newConnector.getConnection(),
                                                                                             assetGUID);

            /*
             * Pass the new connected asset properties to the connector
             */
            newConnector.initializeConnectedAssetProperties(connectedAssetProperties);
        }
        catch (Throwable  error)
        {
            /*
             * Ignore error - connectedAssetProperties is left at null.
             */
        }

        /*
         * At this stage, the asset properties are not retrieved from the server.  This does not happen until the caller
         * issues a connector.getConnectedAssetProperties.  This causes the connectedAssetProperties.refresh() call
         * to be made, which contacts the OMAS server and retrieves the asset properties.
         *
         * Delaying the population of the connected asset properties ensures the latest values are returned to the
         * caller (consider a long running connection).  Alternatively, these properties may not ever be used by the
         * caller so retrieving the properties at this point would be unnecessary.
         */

        return newConnector;
    }


    /**
     * Returns the connection corresponding to the supplied connection GUID.
     *
     * @param restClient client that calls REST APIs
     * @param userId userId of user making request.
     * @param guid   the unique id for the connection within the metadata repository.
     *
     * @return connection instance.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    protected Connection getConnectionByGUID(OCFRESTClient  restClient,
                                             String         userId,
                                             String         guid) throws InvalidParameterException,
                                                                         PropertyServerException,
                                                                         UserNotAuthorizedException
    {
        final String   methodName  = "getConnectionByGUID";
        final String   urlTemplate = "/servers/{0}/open-metadata/common-services/ocf/connected-asset/users/{1}/connections/{2}";

        ConnectionResponse   restResult = restClient.callConnectionGetRESTCall(methodName,
                                                                               serverPlatformRootURL + urlTemplate,
                                                                               serverName,
                                                                               userId,
                                                                               guid);

        exceptionHandler.detectAndThrowInvalidParameterException(methodName, restResult);
        exceptionHandler.detectAndThrowUserNotAuthorizedException(methodName, restResult);
        exceptionHandler.detectAndThrowPropertyServerException(methodName, restResult);

        return restResult.getConnection();
    }



    /**
     * Returns the connection corresponding to the supplied asset GUID.
     *
     * @param restClient client that calls REST APIs
     * @param userId       userId of user making request.
     * @param assetGUID   the unique id for the asset within the metadata repository.
     *
     * @return Connector   connector instance.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    protected Connection getConnectionForAsset(OCFRESTClient  restClient,
                                               String         userId,
                                               String         assetGUID) throws InvalidParameterException,
                                                                                PropertyServerException,
                                                                                UserNotAuthorizedException
    {
        final String   methodName = "getConnectionForAsset";
        final String   urlTemplate = "/servers/{0}/open-metadata/common-services/ocf/connected-asset/users/{1}/assets/{2}/connection";

        ConnectionResponse restResult = restClient.callConnectionGetRESTCall(methodName,
                                                                             serverPlatformRootURL + urlTemplate,
                                                                             serverName,
                                                                             userId,
                                                                             assetGUID);

        exceptionHandler.detectAndThrowInvalidParameterException(methodName, restResult);
        exceptionHandler.detectAndThrowUserNotAuthorizedException(methodName, restResult);
        exceptionHandler.detectAndThrowPropertyServerException(methodName, restResult);

        return restResult.getConnection();
    }


    /**
     * Returns the unique identifier for the asset connected to the requested connection.
     *
     * @param userId the userId of the requesting user.
     * @param connectionGUID  unique identifier for the connection.
     *
     * @return unique identifier of asset.
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server.
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    protected String  getAssetForConnection(OCFRESTClient  restClient,
                                            String         userId,
                                            String         connectionGUID) throws InvalidParameterException,
                                                                                  PropertyServerException,
                                                                                  UserNotAuthorizedException
    {
        final String   methodName = "getAssetForConnection";
        final String   urlTemplate = "/servers/{0}/open-metadata/common-services/ocf/connected-asset/users/{1}/assets/by-connection/{2}";

        GUIDResponse restResult = restClient.callGUIDGetRESTCall(methodName,
                                                                 serverPlatformRootURL + urlTemplate,
                                                                 serverName,
                                                                 userId,
                                                                 connectionGUID);

        exceptionHandler.detectAndThrowInvalidParameterException(methodName, restResult);
        exceptionHandler.detectAndThrowUserNotAuthorizedException(methodName, restResult);
        exceptionHandler.detectAndThrowPropertyServerException(methodName, restResult);

        return restResult.getGUID();
    }
}
