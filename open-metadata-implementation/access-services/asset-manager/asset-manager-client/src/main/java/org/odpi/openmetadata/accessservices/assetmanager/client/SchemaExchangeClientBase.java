/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.assetmanager.client;

import org.odpi.openmetadata.accessservices.assetmanager.api.SchemaExchangeInterface;
import org.odpi.openmetadata.accessservices.assetmanager.client.rest.AssetManagerRESTClient;
import org.odpi.openmetadata.accessservices.assetmanager.metadataelements.ElementHeader;
import org.odpi.openmetadata.accessservices.assetmanager.metadataelements.SchemaAttributeElement;
import org.odpi.openmetadata.accessservices.assetmanager.metadataelements.SchemaTypeElement;
import org.odpi.openmetadata.accessservices.assetmanager.properties.*;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;

import java.util.List;
import java.util.Map;

/**
 * ExchangeClientBase provides the base class for the clients that implement SchemaExchangeInterface
 */
public class SchemaExchangeClientBase extends ExchangeClientBase implements SchemaExchangeInterface
{
    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param auditLog logging destination
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public SchemaExchangeClientBase(String   serverName,
                                    String   serverPlatformURLRoot,
                                    AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, auditLog);
    }


    /**
     * Create a new client with no authentication embedded in the HTTP request.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public SchemaExchangeClientBase(String serverName,
                                    String serverPlatformURLRoot) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot);
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
    public SchemaExchangeClientBase(String   serverName,
                                    String   serverPlatformURLRoot,
                                    String   userId,
                                    String   password,
                                    AuditLog auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, userId, password, auditLog);
    }


    /**
     * Create a new client that is going to be used in an OMAG Server.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformURLRoot the network address of the server running the OMAS REST servers
     * @param restClient client that issues the REST API calls
     * @param maxPageSize maximum number of results supported by this server
     * @param auditLog logging destination
     * @throws InvalidParameterException there is a problem creating the client-side components to issue any
     * REST API calls.
     */
    public SchemaExchangeClientBase(String                 serverName,
                                    String                 serverPlatformURLRoot,
                                    AssetManagerRESTClient restClient,
                                    int                    maxPageSize,
                                    AuditLog               auditLog) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, restClient, maxPageSize, auditLog);
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
    public SchemaExchangeClientBase(String serverName,
                                    String serverPlatformURLRoot,
                                    String userId,
                                    String password) throws InvalidParameterException
    {
        super(serverName, serverPlatformURLRoot, userId, password);
    }



    /* =====================================================================================================================
     * A schemaType describes the structure of a data asset, process or port
     */

    /**
     * Create a new metadata element to represent a schema type.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param assetManagerIsHome ensure that only the asset manager can update this schema element
     * @param schemaTypeExternalIdentifier unique identifier of the schema type in the external asset manager
     * @param schemaTypeExternalIdentifierName name of property for the external identifier in the external asset manager
     * @param schemaTypeExternalIdentifierUsage optional usage description for the external identifier when calling the external asset manager
     * @param schemaTypeExternalIdentifierSource component that issuing this request.
     * @param schemaTypeExternalIdentifierKeyPattern pattern for the external identifier within the external asset manager (default is LOCAL_KEY)
     * @param mappingProperties additional properties to help with the mapping of the elements in the external asset manager and open metadata
     * @param schemaTypeProperties properties about the schema type to store
     *
     * @return unique identifier of the new schema type
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public String createSchemaType(String               userId,
                                   String               assetManagerGUID,
                                   String               assetManagerName,
                                   boolean              assetManagerIsHome,
                                   String               schemaTypeExternalIdentifier,
                                   String               schemaTypeExternalIdentifierName,
                                   String               schemaTypeExternalIdentifierUsage,
                                   String               schemaTypeExternalIdentifierSource,
                                   KeyPattern           schemaTypeExternalIdentifierKeyPattern,
                                   Map<String, String>  mappingProperties,
                                   SchemaTypeProperties schemaTypeProperties) throws InvalidParameterException,
                                                                                     UserNotAuthorizedException,
                                                                                     PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Create a new metadata element to represent a schema type using an existing metadata element as a template.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param templateGUID unique identifier of the metadata element to copy
     * @param schemaTypeExternalIdentifier unique identifier of the schema type in the external asset manager
     * @param schemaTypeExternalIdentifierName name of property for the external identifier in the external asset manager
     * @param schemaTypeExternalIdentifierUsage optional usage description for the external identifier when calling the external asset manager
     * @param schemaTypeExternalIdentifierSource component that issuing this request.
     * @param schemaTypeExternalIdentifierKeyPattern pattern for the external identifier within the external asset manager (default is LOCAL_KEY)
     * @param mappingProperties additional properties to help with the mapping of the elements in the
     *                          external asset manager and open metadata
     * @param templateProperties properties that override the template
     *
     * @return unique identifier of the new schema type
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public String createSchemaTypeFromTemplate(String              userId,
                                               String              assetManagerGUID,
                                               String              assetManagerName,
                                               String              templateGUID,
                                               String              schemaTypeExternalIdentifier,
                                               String              schemaTypeExternalIdentifierName,
                                               String              schemaTypeExternalIdentifierUsage,
                                               String              schemaTypeExternalIdentifierSource,
                                               KeyPattern          schemaTypeExternalIdentifierKeyPattern,
                                               Map<String, String> mappingProperties,
                                               TemplateProperties templateProperties) throws InvalidParameterException,
                                                                                             UserNotAuthorizedException,
                                                                                             PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Update the metadata element representing a schema type.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaTypeGUID unique identifier of the metadata element to update
     * @param schemaTypeExternalIdentifier unique identifier of the schema type in the external asset manager
     * @param isMergeUpdate should the new properties be merged with existing properties (true) or completely replace them (false)?
     * @param schemaTypeProperties new properties for the metadata element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void updateSchemaType(String               userId,
                                 String               assetManagerGUID,
                                 String               assetManagerName,
                                 String               schemaTypeGUID,
                                 String               schemaTypeExternalIdentifier,
                                 boolean              isMergeUpdate,
                                 SchemaTypeProperties schemaTypeProperties) throws InvalidParameterException,
                                                                                   UserNotAuthorizedException,
                                                                                   PropertyServerException
    {
        // todo
    }


    /**
     * Connect a schema type to a data asset, process or port.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param parentElementGUID unique identifier of the open metadata element that this schema type is to be connected to
     * @param parentElementTypeName unique type name of the open metadata element that this schema type is to be connected to
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void setupSchemaTypeParent(String userId,
                                      String assetManagerGUID,
                                      String assetManagerName,
                                      String parentElementGUID,
                                      String parentElementTypeName) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        // todo
    }


    /**
     * Remove the relationship between a schema type and its parent data asset, process or port.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param parentElementGUID unique identifier of the open metadata element that this schema type is to be connected to
     * @param parentElementTypeName unique type name of the open metadata element that this schema type is to be connected to
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void clearSchemaTypeParent(String userId,
                                      String assetManagerGUID,
                                      String assetManagerName,
                                      String parentElementGUID,
                                      String parentElementTypeName) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        // todo
    }


    /**
     * Remove the metadata element representing a schema type.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaTypeGUID unique identifier of the metadata element to remove
     * @param schemaTypeExternalIdentifier unique identifier of the schema type in the external asset manager
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void removeSchemaType(String userId,
                                 String assetManagerGUID,
                                 String assetManagerName,
                                 String schemaTypeGUID,
                                 String schemaTypeExternalIdentifier) throws InvalidParameterException,
                                                                             UserNotAuthorizedException,
                                                                             PropertyServerException
    {
        // todo
    }


    /**
     * Retrieve the list of schema type metadata elements that contain the search string.
     * The search string is treated as a regular expression.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param searchString string to find in the properties
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     *
     * @return list of matching metadata elements
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public List<SchemaTypeElement> findSchemaType(String userId,
                                                  String assetManagerGUID,
                                                  String assetManagerName,
                                                  String searchString,
                                                  int    startFrom,
                                                  int    pageSize) throws InvalidParameterException,
                                                                          UserNotAuthorizedException,
                                                                          PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Return the schema type associated with a specific open metadata element (data asset, process or port).
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param parentElementGUID unique identifier of the open metadata element that this schema type is to be connected to
     * @param parentElementTypeName unique type name of the open metadata element that this schema type is to be connected to
     *
     * @return metadata element describing the schema type associated with the requested parent element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public SchemaTypeElement getSchemaTypeForElement(String userId,
                                                     String assetManagerGUID,
                                                     String assetManagerName,
                                                     String parentElementGUID,
                                                     String parentElementTypeName) throws InvalidParameterException,
                                                                                          UserNotAuthorizedException,
                                                                                          PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the list of schema type metadata elements with a matching qualified or display name.
     * There are no wildcards supported on this request.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param name name to search for
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     *
     * @return list of matching metadata elements
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public List<SchemaTypeElement>   getSchemaTypeByName(String userId,
                                                         String assetManagerGUID,
                                                         String assetManagerName,
                                                         String name,
                                                         int    startFrom,
                                                         int    pageSize) throws InvalidParameterException,
                                                                                 UserNotAuthorizedException,
                                                                                 PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the schema type metadata element with the supplied unique identifier.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaTypeGUID unique identifier of the requested metadata element
     *
     * @return requested metadata element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public SchemaTypeElement getSchemaTypeByGUID(String userId,
                                                 String assetManagerGUID,
                                                 String assetManagerName,
                                                 String schemaTypeGUID) throws InvalidParameterException,
                                                                               UserNotAuthorizedException,
                                                                               PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the header of the metadata element connected to a schema type.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaTypeGUID unique identifier of the requested metadata element
     *
     * @return header for parent element (data asset, process, port)
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public ElementHeader getSchemaTypeParent(String userId,
                                             String assetManagerGUID,
                                             String assetManagerName,
                                             String schemaTypeGUID) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        // todo
        return null;
    }


    /* ===============================================================================
     * A schemaType typically contains many schema attributes, linked with relationships.
     */

    /**
     * Create a new metadata element to represent a schema attribute.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param assetManagerIsHome ensure that only the asset manager can update this schema attribute
     * @param schemaElementGUID unique identifier of the schemaType or Schema Attribute where the schema attribute is connected to
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     * @param schemaAttributeExternalIdentifierName name of property for the external identifier in the external asset manager
     * @param schemaAttributeExternalIdentifierUsage optional usage description for the external identifier when calling the external asset manager
     * @param schemaAttributeExternalIdentifierSource component that issuing this request.
     * @param schemaAttributeExternalIdentifierKeyPattern pattern for the external identifier within the external asset manager (default is LOCAL_KEY)
     * @param mappingProperties additional properties to help with the mapping of the elements in the
     *                          external asset manager and open metadata
     * @param schemaAttributeProperties properties for the schema attribute
     *
     * @return unique identifier of the new metadata element for the schema attribute
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public String createSchemaAttribute(String                    userId,
                                        String                    assetManagerGUID,
                                        String                    assetManagerName,
                                        boolean                   assetManagerIsHome,
                                        String                    schemaElementGUID,
                                        String                    schemaAttributeExternalIdentifier,
                                        String                    schemaAttributeExternalIdentifierName,
                                        String                    schemaAttributeExternalIdentifierUsage,
                                        String                    schemaAttributeExternalIdentifierSource,
                                        KeyPattern                schemaAttributeExternalIdentifierKeyPattern,
                                        Map<String, String>       mappingProperties,
                                        SchemaAttributeProperties schemaAttributeProperties) throws InvalidParameterException,
                                                                                                    UserNotAuthorizedException,
                                                                                                    PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Create a new metadata element to represent a schema attribute using an existing metadata element as a template.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param templateGUID unique identifier of the metadata element to copy
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     * @param schemaAttributeExternalIdentifierName name of property for the external identifier in the external asset manager
     * @param schemaAttributeExternalIdentifierUsage optional usage description for the external identifier when calling the external asset manager
     * @param schemaAttributeExternalIdentifierSource component that issuing this request.
     * @param schemaAttributeExternalIdentifierKeyPattern pattern for the external identifier within the external asset manager (default is LOCAL_KEY)
     * @param mappingProperties additional properties to help with the mapping of the elements in the
     *                          external asset manager and open metadata
     * @param templateProperties properties that override the template
     *
     * @return unique identifier of the new metadata element for the schema attribute
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public String createSchemaAttributeFromTemplate(String              userId,
                                                    String              assetManagerGUID,
                                                    String              assetManagerName,
                                                    String              templateGUID,
                                                    String              schemaAttributeExternalIdentifier,
                                                    String              schemaAttributeExternalIdentifierName,
                                                    String              schemaAttributeExternalIdentifierUsage,
                                                    String              schemaAttributeExternalIdentifierSource,
                                                    KeyPattern          schemaAttributeExternalIdentifierKeyPattern,
                                                    Map<String, String> mappingProperties,
                                                    TemplateProperties  templateProperties) throws InvalidParameterException,
                                                                                                   UserNotAuthorizedException,
                                                                                                   PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Update the properties of the metadata element representing a schema attribute.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaAttributeGUID unique identifier of the schema attribute to update
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     * @param isMergeUpdate should the new properties be merged with existing properties (true) or completely replace them (false)?
     * @param schemaAttributeProperties new properties for the schema attribute
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void updateSchemaAttribute(String                    userId,
                                      String                    assetManagerGUID,
                                      String                    assetManagerName,
                                      String                    schemaAttributeGUID,
                                      String                    schemaAttributeExternalIdentifier,
                                      boolean                   isMergeUpdate,
                                      SchemaAttributeProperties schemaAttributeProperties) throws InvalidParameterException,
                                                                                                  UserNotAuthorizedException,
                                                                                                  PropertyServerException
    {
        // todo
    }


    /**
     * Classify the schema type (or attribute if type is embedded) to indicate that it is a calculated value.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaElementGUID unique identifier of the metadata element to update
     * @param schemaElementExternalIdentifier unique identifier of the schema element in the external asset manager
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void setSchemaElementAsCalculatedValue(String userId,
                                                  String assetManagerGUID,
                                                  String assetManagerName,
                                                  String schemaElementGUID,
                                                  String schemaElementExternalIdentifier,
                                                  String formula) throws InvalidParameterException,
                                                                         UserNotAuthorizedException,
                                                                         PropertyServerException
    {
        // todo
    }


    /**
     * Remove the calculated value designation from the schema element.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaElementGUID unique identifier of the metadata element to update
     * @param schemaElementExternalIdentifier unique identifier of the schema element in the external asset manager
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void clearSchemaElementAsCalculatedValue(String userId,
                                                    String assetManagerGUID,
                                                    String assetManagerName,
                                                    String schemaElementGUID,
                                                    String schemaElementExternalIdentifier) throws InvalidParameterException,
                                                                                                   UserNotAuthorizedException,
                                                                                                   PropertyServerException
    {
        // todo
    }


    /**
     * Link two schema elements together to show how a calculated value is derived.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaElementOneGUID unique identifier of the derived schema element
     * @param schemaElementTwoGUID unique identifier of the query target schema element
     * @param queryId identifier of query (used if place holders are in the "CalculatedValue" formula).
     * @param query query issued to the target
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void setupSchemaQueryTarget(String userId,
                                       String assetManagerGUID,
                                       String assetManagerName,
                                       String schemaElementOneGUID,
                                       String schemaElementTwoGUID,
                                       String queryId,
                                       String query) throws InvalidParameterException,
                                                            UserNotAuthorizedException,
                                                            PropertyServerException
    {
        // todo
    }


    /**
     * Update the relationship properties for the query target.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaElementOneGUID unique identifier of the derived schema element
     * @param schemaElementTwoGUID unique identifier of the query target schema element
     * @param queryId identifier of query (used if place holders are in the "CalculatedValue" formula).
     * @param query query issued to the target
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void updateSchemaQueryTarget(String userId,
                                        String assetManagerGUID,
                                        String assetManagerName,
                                        String schemaElementOneGUID,
                                        String schemaElementTwoGUID,
                                        String queryId,
                                        String query) throws InvalidParameterException,
                                                             UserNotAuthorizedException,
                                                             PropertyServerException
    {
        // todo
    }


    /**
     * Remove the relationship between two schema elements.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaElementOneGUID unique identifier of the derived schema element
     * @param schemaElementTwoGUID unique identifier of the query target schema element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void clearSchemaQueryTarget(String userId,
                                       String assetManagerGUID,
                                       String assetManagerName,
                                       String schemaElementOneGUID,
                                       String schemaElementTwoGUID) throws InvalidParameterException,
                                                                           UserNotAuthorizedException,
                                                                           PropertyServerException
    {
        // todo
    }


    /**
     * Classify the column schema attribute to indicate that it describes a primary key.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param assetManagerIsHome ensure that only the asset manager can update this classification
     * @param schemaAttributeGUID unique identifier of the metadata element to update
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     * @param primaryKeyName name of the primary key (if different from the column name)
     * @param primaryKeyPattern key pattern used to maintain the primary key
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void setupColumnAsPrimaryKey(String     userId,
                                        String     assetManagerGUID,
                                        String     assetManagerName,
                                        boolean    assetManagerIsHome,
                                        String     schemaAttributeGUID,
                                        String     schemaAttributeExternalIdentifier,
                                        String     primaryKeyName,
                                        KeyPattern primaryKeyPattern) throws InvalidParameterException,
                                                                             UserNotAuthorizedException,
                                                                             PropertyServerException
    {
        // todo
    }


    /**
     * Remove the primary key designation from the schema attribute.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaAttributeGUID unique identifier of the metadata element to update
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void clearColumnAsPrimaryKey(String userId,
                                        String assetManagerGUID,
                                        String assetManagerName,
                                        String schemaAttributeGUID,
                                        String schemaAttributeExternalIdentifier) throws InvalidParameterException,
                                                                                         UserNotAuthorizedException,
                                                                                         PropertyServerException
    {
        // todo
    }


    /**
     * Link two schema attributes together to show a foreign key relationship.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param assetManagerIsHome ensure that only the asset manager can update this relationship
     * @param primaryKeyGUID unique identifier of the derived schema element
     * @param foreignKeyGUID unique identifier of the query target schema element
     * @param foreignKeyProperties properties for the foreign key relationship
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void setupForeignKeyRelationship(String               userId,
                                            String               assetManagerGUID,
                                            String               assetManagerName,
                                            boolean              assetManagerIsHome,
                                            String               primaryKeyGUID,
                                            String               foreignKeyGUID,
                                            ForeignKeyProperties foreignKeyProperties) throws InvalidParameterException,
                                                                                              UserNotAuthorizedException,
                                                                                              PropertyServerException
    {
        // todo
    }


    /**
     * Update the relationship properties for the query target.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param primaryKeyGUID unique identifier of the derived schema element
     * @param foreignKeyGUID unique identifier of the query target schema element
     * @param foreignKeyProperties properties for the foreign key relationship
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void updateForeignKeyRelationship(String               userId,
                                             String               assetManagerGUID,
                                             String               assetManagerName,
                                             String               primaryKeyGUID,
                                             String               foreignKeyGUID,
                                             ForeignKeyProperties foreignKeyProperties) throws InvalidParameterException,
                                                                                               UserNotAuthorizedException,
                                                                                               PropertyServerException
    {
        // todo
    }


    /**
     * Remove the foreign key relationship between two schema elements.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param primaryKeyGUID unique identifier of the derived schema element
     * @param foreignKeyGUID unique identifier of the query target schema element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void clearForeignKeyRelationship(String userId,
                                            String assetManagerGUID,
                                            String assetManagerName,
                                            String primaryKeyGUID,
                                            String foreignKeyGUID) throws InvalidParameterException,
                                                                          UserNotAuthorizedException,
                                                                          PropertyServerException
    {
        // todo
    }


    /**
     * Remove the metadata element representing a schema attribute.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaAttributeGUID unique identifier of the metadata element to remove
     * @param schemaAttributeExternalIdentifier unique identifier of the schema attribute in the external asset manager
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public void removeSchemaAttribute(String userId,
                                      String assetManagerGUID,
                                      String assetManagerName,
                                      String schemaAttributeGUID,
                                      String schemaAttributeExternalIdentifier) throws InvalidParameterException,
                                                                                       UserNotAuthorizedException,
                                                                                       PropertyServerException
    {
        // todo
    }


    /**
     * Retrieve the list of schema attribute metadata elements that contain the search string.
     * The search string is treated as a regular expression.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param searchString string to find in the properties
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     *
     * @return list of matching metadata elements
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public List<SchemaAttributeElement>   findSchemaAttributes(String userId,
                                                               String assetManagerGUID,
                                                               String assetManagerName,
                                                               String searchString,
                                                               int    startFrom,
                                                               int    pageSize) throws InvalidParameterException,
                                                                                       UserNotAuthorizedException,
                                                                                       PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the list of schema attributes associated with a schemaType.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaTypeGUID unique identifier of the schemaType of interest
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     *
     * @return list of associated metadata elements
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public List<SchemaAttributeElement>    getAttributesForSchemaType(String userId,
                                                                      String assetManagerGUID,
                                                                      String assetManagerName,
                                                                      String schemaTypeGUID,
                                                                      int    startFrom,
                                                                      int    pageSize) throws InvalidParameterException,
                                                                                              UserNotAuthorizedException,
                                                                                              PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the list of schema attribute metadata elements with a matching qualified or display name.
     * There are no wildcards supported on this request.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param name name to search for
     * @param startFrom paging start point
     * @param pageSize maximum results that can be returned
     *
     * @return list of matching metadata elements
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public List<SchemaAttributeElement>   getSchemaAttributesByName(String userId,
                                                                    String assetManagerGUID,
                                                                    String assetManagerName,
                                                                    String name,
                                                                    int    startFrom,
                                                                    int    pageSize) throws InvalidParameterException,
                                                                                            UserNotAuthorizedException,
                                                                                            PropertyServerException
    {
        // todo
        return null;
    }


    /**
     * Retrieve the schema attribute metadata element with the supplied unique identifier.
     *
     * @param userId calling user
     * @param assetManagerGUID unique identifier of software server capability representing the caller
     * @param assetManagerName unique name of software server capability representing the caller
     * @param schemaAttributeGUID unique identifier of the requested metadata element
     *
     * @return matching metadata element
     *
     * @throws InvalidParameterException  one of the parameters is invalid
     * @throws UserNotAuthorizedException the user is not authorized to issue this request
     * @throws PropertyServerException    there is a problem reported in the open metadata server(s)
     */
    public SchemaAttributeElement getSchemaAttributeByGUID(String userId,
                                                           String assetManagerGUID,
                                                           String assetManagerName,
                                                           String schemaAttributeGUID) throws InvalidParameterException,
                                                                                              UserNotAuthorizedException,
                                                                                              PropertyServerException
    {
        // todo
        return null;
    }
}
