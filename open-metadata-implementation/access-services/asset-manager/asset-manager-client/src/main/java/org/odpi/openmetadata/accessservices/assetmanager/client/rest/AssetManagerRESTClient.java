/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.assetmanager.client.rest;


import org.odpi.openmetadata.accessservices.assetmanager.rest.*;
import org.odpi.openmetadata.commonservices.ffdc.rest.FFDCRESTClient;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;


/**
 * AssetManagerRESTClient is responsible for issuing calls to the OMAS REST APIs.
 */
public class AssetManagerRESTClient extends FFDCRESTClient
{
    /**
     * Constructor for no authentication with audit log.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server manager where the OMAG Server is running.
     * @param auditLog destination for log messages.
     *
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public AssetManagerRESTClient(String   serverName,
                                  String   serverPlatformURLRoot,
                                  AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, auditLog);
    }


    /**
     * Constructor for no authentication.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server manager where the OMAG Server is running.
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public AssetManagerRESTClient(String serverName,
                                  String serverPlatformURLRoot) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot);
    }


    /**
     * Constructor for simple userId and password authentication with audit log.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server manager where the OMAG Server is running.
     * @param userId user id for the HTTP request
     * @param password password for the HTTP request
     * @param auditLog destination for log messages.
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public AssetManagerRESTClient(String   serverName,
                                  String   serverPlatformURLRoot,
                                  String   userId,
                                  String   password,
                                  AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, userId, password, auditLog);
    }


    /**
     * Constructor for simple userId and password authentication.
     *
     * @param serverName name of the OMAG Server to call
     * @param serverPlatformURLRoot URL root of the server manager where the OMAG Server is running.
     * @param userId user id for the HTTP request
     * @param password password for the HTTP request
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public AssetManagerRESTClient(String serverName,
                                  String serverPlatformURLRoot,
                                  String userId,
                                  String password) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, userId, password);
    }



    /**
     * Issue a POST REST call that returns a GlossaryElementResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryElementResponse callGlossaryPostRESTCall(String    methodName,
                                                            String    urlTemplate,
                                                            Object    requestBody,
                                                            Object... params) throws InvalidParameterException,
                                                                                     UserNotAuthorizedException,
                                                                                     PropertyServerException
    {
        GlossaryElementResponse restResult = this.callPostRESTCall(methodName, GlossaryElementResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a GlossaryElementsResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryElementsResponse callGlossariesPostRESTCall(String    methodName,
                                                               String    urlTemplate,
                                                               Object    requestBody,
                                                               Object... params) throws InvalidParameterException,
                                                                                        UserNotAuthorizedException,
                                                                                        PropertyServerException
    {
        GlossaryElementsResponse restResult = this.callPostRESTCall(methodName, GlossaryElementsResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a GlossaryCategoryElementResponse object.
     *
     * @param methodName  name of the method being called
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryCategoryElementResponse callGlossaryCategoryPostRESTCall(String    methodName,
                                                                            String    urlTemplate,
                                                                            Object    requestBody,
                                                                            Object... params) throws InvalidParameterException,
                                                                                                     UserNotAuthorizedException,
                                                                                                     PropertyServerException
    {
        GlossaryCategoryElementResponse restResult = this.callPostRESTCall(methodName, GlossaryCategoryElementResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a GlossaryCategoryElementsResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryCategoryElementsResponse callGlossaryCategoriesPostRESTCall(String    methodName,
                                                                               String    urlTemplate,
                                                                               Object    requestBody,
                                                                               Object... params) throws InvalidParameterException,
                                                                                                        UserNotAuthorizedException,
                                                                                                        PropertyServerException
    {
        GlossaryCategoryElementsResponse restResult = this.callPostRESTCall(methodName, GlossaryCategoryElementsResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a GlossaryTermElementResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryTermElementResponse callGlossaryTermPostRESTCall(String    methodName,
                                                                    String    urlTemplate,
                                                                    Object    requestBody,
                                                                    Object... params) throws InvalidParameterException,
                                                                                             UserNotAuthorizedException,
                                                                                             PropertyServerException
    {
        GlossaryTermElementResponse restResult = this.callPostRESTCall(methodName, GlossaryTermElementResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a GlossaryTermElementsResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public GlossaryTermElementsResponse callGlossaryTermsPostRESTCall(String    methodName,
                                                                      String    urlTemplate,
                                                                      Object    requestBody,
                                                                      Object... params) throws InvalidParameterException,
                                                                                               UserNotAuthorizedException,
                                                                                               PropertyServerException
    {
        GlossaryTermElementsResponse restResult = this.callPostRESTCall(methodName, GlossaryTermElementsResponse.class, urlTemplate, requestBody, params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a GET REST call that returns a ExternalGlossaryLinkElementsResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public ExternalGlossaryLinkElementsResponse callExternalGlossaryLinksGetRESTCall(String    methodName,
                                                                                     String    urlTemplate,
                                                                                     Object... params) throws InvalidParameterException,
                                                                                                              UserNotAuthorizedException,
                                                                                                              PropertyServerException
    {
        ExternalGlossaryLinkElementsResponse restResult = this.callGetRESTCall(methodName,
                                                                               ExternalGlossaryLinkElementsResponse.class,
                                                                               urlTemplate,
                                                                               params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }


    /**
     * Issue a POST REST call that returns a ElementHeadersResponse object.
     *
     * @param methodName  name of the method being called.
     * @param urlTemplate template of the URL for the REST API call with place-holders for the parameters.
     * @param requestBody object that passes additional parameters
     * @param params      a list of parameters that are slotted into the url template.
     *
     * @return response object
     * @throws InvalidParameterException one of the parameters is invalid.
     * @throws UserNotAuthorizedException the user is not authorized to make this request.
     * @throws PropertyServerException the repository is not available or not working properly.
     */
    public ElementHeadersResponse callElementHeadersPostRESTCall(String    methodName,
                                                                 String    urlTemplate,
                                                                 Object    requestBody,
                                                                 Object... params) throws InvalidParameterException,
                                                                                          UserNotAuthorizedException,
                                                                                          PropertyServerException
    {
        ElementHeadersResponse restResult = this.callPostRESTCall(methodName,
                                                                  ElementHeadersResponse.class,
                                                                  urlTemplate,
                                                                  requestBody,
                                                                  params);

        exceptionHandler.detectAndThrowStandardExceptions(methodName, restResult);

        return restResult;
    }
}
