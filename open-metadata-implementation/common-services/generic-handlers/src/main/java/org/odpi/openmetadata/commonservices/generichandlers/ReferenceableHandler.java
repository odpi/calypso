/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.commonservices.generichandlers;

import org.odpi.openmetadata.commonservices.ffdc.InvalidParameterHandler;
import org.odpi.openmetadata.commonservices.repositoryhandler.RepositoryHandler;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.PropertyServerException;
import org.odpi.openmetadata.frameworks.connectors.ffdc.UserNotAuthorizedException;
import org.odpi.openmetadata.metadatasecurity.server.OpenMetadataServerSecurityVerifier;
import org.odpi.openmetadata.frameworks.auditlog.AuditLog;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.InstanceProperties;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.repositoryconnector.OMRSRepositoryHelper;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.TypeErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * ReferenceableHandler manages methods on generic referenceables.
 */
public class ReferenceableHandler<B> extends OpenMetadataAPIGenericHandler<B>
{
    /**
     * Construct the handler information needed to interact with the repository services
     *
     * @param converter specific converter for this bean class
     * @param beanClass name of bean class that is represented by the generic class B
     * @param serviceName      name of this service
     * @param serverName       name of the local server
     * @param invalidParameterHandler handler for managing parameter errors
     * @param repositoryHandler     manages calls to the repository services
     * @param repositoryHelper provides utilities for manipulating the repository services objects
     * @param localServerUserId userId for this server
     * @param securityVerifier open metadata security services verifier
     * @param supportedZones list of zones that the access service is allowed to serve Asset instances from.
     * @param defaultZones list of zones that the access service should set in all new Asset instances.
     * @param publishZones list of zones that the access service sets up in published Asset instances.
     * @param auditLog destination for audit log events.
     */
    public ReferenceableHandler(OpenMetadataAPIGenericConverter<B> converter,
                                Class<B>                           beanClass,
                                String                             serviceName,
                                String                             serverName,
                                InvalidParameterHandler            invalidParameterHandler,
                                RepositoryHandler                  repositoryHandler,
                                OMRSRepositoryHelper               repositoryHelper,
                                String                             localServerUserId,
                                OpenMetadataServerSecurityVerifier securityVerifier,
                                List<String>                       supportedZones,
                                List<String>                       defaultZones,
                                List<String>                       publishZones,
                                AuditLog                           auditLog)
    {
        super(converter,
              beanClass,
              serviceName,
              serverName,
              invalidParameterHandler,
              repositoryHandler,
              repositoryHelper,
              localServerUserId,
              securityVerifier,
              supportedZones,
              defaultZones,
              publishZones,
              auditLog);
    }


    /**
     * Return the unique identifier of the bean with the requested qualified name.
     * The match is exact.  It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param methodName calling method
     *
     * @return matching B bean
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public String getBeanGUIDByQualifiedName(String       userId,
                                             String       typeGUID,
                                             String       typeName,
                                             String       name,
                                             String       nameParameterName,
                                             String       methodName) throws InvalidParameterException,
                                                                             PropertyServerException,
                                                                             UserNotAuthorizedException
    {
        return this.getBeanGUIDByQualifiedName(userId,
                                               typeGUID,
                                               typeName,
                                               name,
                                               nameParameterName,
                                               supportedZones,
                                               methodName);
    }


    /**
     * Return the bean with the requested qualified name.
     * The match is exact.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param methodName calling method
     *
     * @return matching B bean
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public String getBeanGUIDByQualifiedName(String       userId,
                                             String       typeGUID,
                                             String       typeName,
                                             String       name,
                                             String       nameParameterName,
                                             List<String> serviceSupportedZones,
                                             String       methodName) throws InvalidParameterException,
                                                                             PropertyServerException,
                                                                             UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        return this.getBeanGUIDByUniqueName(userId,
                                            name,
                                            nameParameterName,
                                            OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                            resultTypeGUID,
                                            resultTypeName,
                                            serviceSupportedZones,
                                            methodName);
    }


    /**
     * Return the bean with the requested qualified name.
     * The match is exact. It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param methodName calling method
     *
     * @return matching B bean
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public B getBeanByQualifiedName(String       userId,
                                    String       typeGUID,
                                    String       typeName,
                                    String       name,
                                    String       nameParameterName,
                                    String       methodName) throws InvalidParameterException,
                                                                    PropertyServerException,
                                                                    UserNotAuthorizedException
    {
        return this.getBeanByQualifiedName(userId,
                                           typeGUID,
                                           typeName,
                                           name,
                                           nameParameterName,
                                           supportedZones,
                                           methodName);
    }


    /**
     * Return the bean with the requested qualified name.
     * The match is exact.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param methodName calling method
     *
     * @return matching B bean
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public B getBeanByQualifiedName(String       userId,
                                    String       typeGUID,
                                    String       typeName,
                                    String       name,
                                    String       nameParameterName,
                                    List<String> serviceSupportedZones,
                                    String       methodName) throws InvalidParameterException,
                                                                    PropertyServerException,
                                                                    UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.ASSET_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.ASSET_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        return this.getBeanByUniqueName(userId,
                                        name,
                                        nameParameterName,
                                        OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME,
                                        resultTypeGUID,
                                        resultTypeName,
                                        serviceSupportedZones,
                                        methodName);
    }


    /**
     * Return a list of unique identifiers for referenceables with the requested qualified name.
     * The match is exact. It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<String> getBeanGUIDsByQualifiedName(String       userId,
                                                    String       typeGUID,
                                                    String       typeName,
                                                    String       name,
                                                    String       nameParameterName,
                                                    int          startFrom,
                                                    int          pageSize,
                                                    String       methodName) throws InvalidParameterException,
                                                                                    PropertyServerException,
                                                                                    UserNotAuthorizedException
    {
        return this.getBeanGUIDsByQualifiedName(userId,
                                                typeGUID,
                                                typeName,
                                                name,
                                                nameParameterName,
                                                supportedZones,
                                                startFrom,
                                                pageSize,
                                                methodName);
    }


    /**
     * Return a list of unique identifiers for referenceables with the requested qualified name.
     * The match is exact.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<String> getBeanGUIDsByQualifiedName(String       userId,
                                                    String       typeGUID,
                                                    String       typeName,
                                                    String       name,
                                                    String       nameParameterName,
                                                    List<String> serviceSupportedZones,
                                                    int          startFrom,
                                                    int          pageSize,
                                                    String       methodName) throws InvalidParameterException,
                                                                                    PropertyServerException,
                                                                                    UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        List<String> specificMatchPropertyNames = new ArrayList<>();
        specificMatchPropertyNames.add(OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME);

        return this.getBeanGUIDsByValue(userId,
                                        name,
                                        nameParameterName,
                                        resultTypeGUID,
                                        resultTypeName,
                                        specificMatchPropertyNames,
                                        true,
                                        serviceSupportedZones,
                                        startFrom,
                                        pageSize,
                                        methodName);
    }


    /**
     * Return a list of referenceables with the requested qualified name.
     * The match is exact.  It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<B> getBeansByQualifiedName(String   userId,
                                           String   typeGUID,
                                           String   typeName,
                                           String   name,
                                           String   nameParameterName,
                                           int      startFrom,
                                           int      pageSize,
                                           String   methodName) throws InvalidParameterException,
                                                                       PropertyServerException,
                                                                       UserNotAuthorizedException
    {
        return getBeansByQualifiedName(userId,
                                       typeGUID,
                                       typeName,
                                       name,
                                       nameParameterName,
                                       supportedZones,
                                       startFrom,
                                       pageSize,
                                       methodName);
    }


    /**
     * Return a list of referenceables with the requested qualified name.
     * The match is exact.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<B> getBeansByQualifiedName(String       userId,
                                           String       typeGUID,
                                           String       typeName,
                                           String       name,
                                           String       nameParameterName,
                                           List<String> serviceSupportedZones,
                                           int          startFrom,
                                           int          pageSize,
                                           String       methodName) throws InvalidParameterException,
                                                                           PropertyServerException,
                                                                           UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.ASSET_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.ASSET_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        List<String> specificMatchPropertyNames = new ArrayList<>();
        specificMatchPropertyNames.add(OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME);

        return this.getBeansByValue(userId,
                                    name,
                                    nameParameterName,
                                    resultTypeGUID,
                                    resultTypeName,
                                    specificMatchPropertyNames,
                                    true,
                                    serviceSupportedZones,
                                    startFrom,
                                    pageSize,
                                    methodName);
    }



    /**
     * Return a list of unique identifiers for referenceables with the requested qualified name.
     * The match is via a Regular Expression (RegEx).  It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for - this is a regular expression (RegEx)
     * @param nameParameterName property that provided the name
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<String> findBeanGUIDsByQualifiedName(String       userId,
                                                     String       typeGUID,
                                                     String       typeName,
                                                     String       name,
                                                     String       nameParameterName,
                                                     int          startFrom,
                                                     int          pageSize,
                                                     String       methodName) throws InvalidParameterException,
                                                                                     PropertyServerException,
                                                                                     UserNotAuthorizedException
    {
        return this.findBeanGUIDsByQualifiedName(userId,
                                                 typeGUID,
                                                 typeName,
                                                 name,
                                                 nameParameterName,
                                                 supportedZones,
                                                 startFrom,
                                                 pageSize,
                                                 methodName);
    }


    /**
     * Return a list of unique identifiers for referenceables with the requested qualified name.
     * The match is via a Regular Expression (RegEx).
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for - this is a regular expression (RegEx)
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<String> findBeanGUIDsByQualifiedName(String       userId,
                                                     String       typeGUID,
                                                     String       typeName,
                                                     String       name,
                                                     String       nameParameterName,
                                                     List<String> serviceSupportedZones,
                                                     int          startFrom,
                                                     int          pageSize,
                                                     String       methodName) throws InvalidParameterException,
                                                                                     PropertyServerException,
                                                                                     UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.ASSET_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.ASSET_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        List<String> specificMatchPropertyNames = new ArrayList<>();
        specificMatchPropertyNames.add(OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME);

        return this.getBeanGUIDsByValue(userId,
                                        name,
                                        nameParameterName,
                                        resultTypeGUID,
                                        resultTypeName,
                                        specificMatchPropertyNames,
                                        false,
                                        serviceSupportedZones,
                                        startFrom,
                                        pageSize,
                                        methodName);
    }


    /**
     * Return a list of referenceables with the requested qualified name.
     * The match is via a Regular Expression (RegEx).  It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for - this is a regular expression (RegEx)
     * @param nameParameterName property that provided the name
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<B> findBeansByQualifiedName(String   userId,
                                            String   typeGUID,
                                            String   typeName,
                                            String   name,
                                            String   nameParameterName,
                                            int      startFrom,
                                            int      pageSize,
                                            String   methodName) throws InvalidParameterException,
                                                                        PropertyServerException,
                                                                        UserNotAuthorizedException
    {
        return findBeansByQualifiedName(userId,
                                        typeGUID,
                                        typeName,
                                        name,
                                        nameParameterName,
                                        supportedZones,
                                        startFrom,
                                        pageSize,
                                        methodName);
    }


    /**
     * Return a list of referenceables with the requested qualified name.
     * The match is via a Regular Expression (RegEx).
     *
     * @param userId calling user
     * @param typeGUID unique identifier of the asset type to search for (null for the generic Asset type)
     * @param typeName unique identifier of the asset type to search for (null for the generic Asset type)
     * @param name name to search for - this is a regular expression (RegEx)
     * @param nameParameterName property that provided the name
     * @param serviceSupportedZones list of supported zones for this service
     * @param startFrom starting element (used in paging through large result sets)
     * @param pageSize maximum number of results to return
     * @param methodName calling method
     *
     * @return list of B beans
     *
     * @throws InvalidParameterException one of the parameters is null or invalid.
     * @throws PropertyServerException there is a problem retrieving information from the property server(s).
     * @throws UserNotAuthorizedException the requesting user is not authorized to issue this request.
     */
    public List<B> findBeansByQualifiedName(String       userId,
                                            String       typeGUID,
                                            String       typeName,
                                            String       name,
                                            String       nameParameterName,
                                            List<String> serviceSupportedZones,
                                            int          startFrom,
                                            int          pageSize,
                                            String       methodName) throws InvalidParameterException,
                                                                            PropertyServerException,
                                                                            UserNotAuthorizedException
    {
        String resultTypeGUID = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_GUID;
        String resultTypeName = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME;

        if (typeGUID != null)
        {
            resultTypeGUID = typeGUID;
        }
        if (typeName != null)
        {
            resultTypeName = typeName;
        }

        List<String> specificMatchPropertyNames = new ArrayList<>();
        specificMatchPropertyNames.add(OpenMetadataAPIMapper.QUALIFIED_NAME_PROPERTY_NAME);

        return this.getBeansByValue(userId,
                                    name,
                                    nameParameterName,
                                    resultTypeGUID,
                                    resultTypeName,
                                    specificMatchPropertyNames,
                                    false,
                                    serviceSupportedZones,
                                    startFrom,
                                    pageSize,
                                    methodName);
    }


    /**
     * Add or replace the security tags for a referenceable.
     *
     * @param userId calling user
     * @param beanGUID unique identifier of bean
     * @param beanGUIDParameterName name of parameter supplying the beanGUID
     * @param securityLabels list of security labels defining the security characteristics of the element
     * @param securityProperties Descriptive labels describing origin of the asset
     * @param methodName calling method
     * @throws InvalidParameterException entity not known, null userId or guid
     * @throws PropertyServerException problem accessing property server
     * @throws UserNotAuthorizedException security access problem
     */
    public void  addSecurityTags(String                userId,
                                 String                beanGUID,
                                 String                beanGUIDParameterName,
                                 List<String>          securityLabels,
                                 Map<String, Object>   securityProperties,
                                 String                methodName) throws InvalidParameterException,
                                                                          UserNotAuthorizedException,
                                                                          PropertyServerException
    {
        ReferenceableBuilder builder = new ReferenceableBuilder(OpenMetadataAPIMapper.REFERENCEABLE_TYPE_GUID,
                                                                OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME,
                                                                repositoryHelper,
                                                                serviceName,
                                                                serverName);

        this.setClassificationInRepository(userId,
                                           beanGUID,
                                           beanGUIDParameterName,
                                           OpenMetadataAPIMapper.ASSET_TYPE_NAME,
                                           OpenMetadataAPIMapper.SECURITY_TAG_CLASSIFICATION_TYPE_GUID,
                                           OpenMetadataAPIMapper.SECURITY_TAG_CLASSIFICATION_TYPE_NAME,
                                           builder.getSecurityTagProperties(securityLabels, securityProperties, methodName),
                                           methodName);
    }


    /**
     * Remove the security tags classification from a referenceable.
     *
     * @param userId calling user
     * @param beanGUID unique identifier of entity to update
     * @param beanGUIDParameterName name of parameter providing beanGUID
     * @param methodName calling method
     * @throws InvalidParameterException entity not known, null userId or guid
     * @throws PropertyServerException problem accessing property server
     * @throws UserNotAuthorizedException security access problem
     */
    public void  removeSecurityTags(String userId,
                                    String beanGUID,
                                    String beanGUIDParameterName,
                                    String methodName) throws InvalidParameterException,
                                                              UserNotAuthorizedException,
                                                              PropertyServerException
    {
        this.removeClassificationFromRepository(userId,
                                                null,
                                                null,
                                                beanGUID,
                                                beanGUIDParameterName,
                                                OpenMetadataAPIMapper.ASSET_TYPE_NAME,
                                                OpenMetadataAPIMapper.SECURITY_TAG_CLASSIFICATION_TYPE_GUID,
                                                OpenMetadataAPIMapper.SECURITY_TAG_CLASSIFICATION_TYPE_NAME,
                                                methodName);
    }



    /**
     * Returns the list of related assets for the asset.  It uses the supportedZones supplied with the service.
     *
     * @param userId       String   userId of user making request.
     * @param startingGUID    String   unique id for element.
     * @param startingGUIDParameterName name of parameter supplying the GUID
     * @param suppliedStartingTypeName name of the type of object that the search begins with - null means referenceable
     * @param suppliedResultingTypeName name of the type of object that the search returns - null mean referenceable
     * @param startFrom int      starting position for fist returned element.
     * @param pageSize  int      maximum number of elements to return on the call.
     * @param methodName String calling method
     *
     * @return a list of assets or
     * @throws InvalidParameterException - the GUID is not recognized or the paging values are invalid or
     * @throws PropertyServerException - there is a problem retrieving the asset properties from the property server or
     * @throws UserNotAuthorizedException - the requesting user is not authorized to issue this request.
     */
    public List<B> getMoreInformation(String       userId,
                                      String       startingGUID,
                                      String       startingGUIDParameterName,
                                      String       suppliedStartingTypeName,
                                      String       suppliedResultingTypeName,
                                      int          startFrom,
                                      int          pageSize,
                                      String       methodName) throws InvalidParameterException,
                                                                      PropertyServerException,
                                                                      UserNotAuthorizedException
    {
        return this.getMoreInformation(userId,
                                       startingGUID,
                                       startingGUIDParameterName,
                                       suppliedStartingTypeName,
                                       suppliedResultingTypeName,
                                       supportedZones,
                                       startFrom,
                                       pageSize,
                                       methodName);
    }


    /**
     * Returns the list of related assets for the asset.
     *
     * @param userId       String   userId of user making request.
     * @param startingGUID    String   unique id for element.
     * @param startingGUIDParameterName name of parameter supplying the GUID
     * @param suppliedStartingTypeName name of the type of object that the search begins with - null means referenceable
     * @param suppliedResultingTypeName name of the type of object that the search returns - null mean referenceable
     * @param serviceSupportedZones supported zones for calling service
     * @param startFrom int      starting position for fist returned element.
     * @param pageSize  int      maximum number of elements to return on the call.
     * @param methodName String calling method
     *
     * @return a list of assets or
     * @throws InvalidParameterException - the GUID is not recognized or the paging values are invalid or
     * @throws PropertyServerException - there is a problem retrieving the asset properties from the property server or
     * @throws UserNotAuthorizedException - the requesting user is not authorized to issue this request.
     */
    public List<B> getMoreInformation(String       userId,
                                      String       startingGUID,
                                      String       startingGUIDParameterName,
                                      String       suppliedStartingTypeName,
                                      String       suppliedResultingTypeName,
                                      List<String> serviceSupportedZones,
                                      int          startFrom,
                                      int          pageSize,
                                      String       methodName) throws InvalidParameterException,
                                                                      PropertyServerException,
                                                                      UserNotAuthorizedException
    {
        String startingTypeName  = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME;
        String resultingTypeName = OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME;

        if (suppliedStartingTypeName != null)
        {
            startingTypeName = suppliedStartingTypeName;
        }
        if (suppliedResultingTypeName != null)
        {
            resultingTypeName = suppliedResultingTypeName;
        }

        return this.getAttachedElements(userId,
                                        startingGUID,
                                        startingGUIDParameterName,
                                        startingTypeName,
                                        OpenMetadataAPIMapper.REFERENCEABLE_TO_MORE_INFO_TYPE_GUID,
                                        OpenMetadataAPIMapper.REFERENCEABLE_TO_MORE_INFO_TYPE_NAME,
                                        resultingTypeName,
                                        serviceSupportedZones,
                                        startFrom,
                                        pageSize,
                                        methodName);
    }


    /**
     * Create a simple relationship between a glossary term and a referenceable.
     *
     * @param userId calling user
     * @param externalSourceGUID guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName name of the software server capability entity that represented the external source - null for local
     * @param beanGUID unique identifier of the asset that is being described
     * @param beanGUIDParameter parameter supply the beanGUID
     * @param glossaryTermGUID unique identifier of the glossary term
     * @param glossaryTermGUIDParameter parameter supplying the list of GlossaryTermGUID
     * @param methodName calling method
     *
     * @throws InvalidParameterException the guid properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException problem accessing the property server
     */
    public void  saveSemanticAssignment(String userId,
                                        String externalSourceGUID,
                                        String externalSourceName,
                                        String beanGUID,
                                        String beanGUIDParameter,
                                        String glossaryTermGUID,
                                        String glossaryTermGUIDParameter,
                                        String methodName)  throws InvalidParameterException,
                                                                   PropertyServerException,
                                                                   UserNotAuthorizedException
    {
        this.linkElementToElement(userId,
                                  externalSourceGUID,
                                  externalSourceName,
                                  beanGUID,
                                  beanGUIDParameter,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME,
                                  glossaryTermGUID,
                                  glossaryTermGUIDParameter,
                                  OpenMetadataAPIMapper.GLOSSARY_TERM_TYPE_NAME,
                                  supportedZones,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_GUID,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_NAME,
                                  null,
                                  methodName);
    }



    /**
     * Create a simple relationship between a glossary term and a referenceable.
     *
     * @param userId calling user
     * @param externalSourceGUID guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName name of the software server capability entity that represented the external source - null for local
     * @param beanGUID unique identifier of the asset that is being described
     * @param beanGUIDParameter parameter supply the beanGUID
     * @param glossaryTermGUID unique identifier of the glossary term
     * @param glossaryTermGUIDParameter parameter supplying the list of GlossaryTermGUID
     * @param description description of the assignment
     * @param expression expression used to determine the assignment
     * @param statusOrdinal the status of the assignment
     * @param confidence how confident is the assignment - no confidence = 0 to complete confidence = 100
     * @param createdBy who/what created the assignment
     * @param steward which steward is responsible for assignment
     * @param source where was the source of the assignment
     * @param methodName calling method
     *
     * @throws InvalidParameterException the guid properties are invalid
     * @throws UserNotAuthorizedException user not authorized to issue this request
     * @throws PropertyServerException problem accessing the property server
     */
    public void  saveSemanticAssignment(String userId,
                                        String externalSourceGUID,
                                        String externalSourceName,
                                        String beanGUID,
                                        String beanGUIDParameter,
                                        String glossaryTermGUID,
                                        String glossaryTermGUIDParameter,
                                        String description,
                                        String expression,
                                        int    statusOrdinal,
                                        int    confidence,
                                        String createdBy,
                                        String steward,
                                        String source,
                                        String methodName)  throws InvalidParameterException,
                                                                   PropertyServerException,
                                                                   UserNotAuthorizedException
    {
        InstanceProperties properties = null;

        if (description != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      null,
                                                                      OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_DESCRIPTION_PROPERTY_NAME,
                                                                      description,
                                                                      methodName);
        }

        if (expression != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_EXPRESSION_PROPERTY_NAME,
                                                                      expression,
                                                                      methodName);
        }

        try
        {
            properties = repositoryHelper.addEnumPropertyToInstance(serviceName,
                                                                    properties,
                                                                    OpenMetadataAPIMapper.STATUS_PROPERTY_NAME,
                                                                    OpenMetadataAPIMapper.TERM_ASSIGNMENT_STATUS_ENUM_TYPE_GUID,
                                                                    OpenMetadataAPIMapper.TERM_ASSIGNMENT_STATUS_ENUM_TYPE_NAME,
                                                                    statusOrdinal,
                                                                    methodName);
        }
        catch (TypeErrorException error)
        {
            errorHandler.handleUnsupportedType(error, methodName, OpenMetadataAPIMapper.TERM_ASSIGNMENT_STATUS_ENUM_TYPE_NAME);
        }

        properties = repositoryHelper.addIntPropertyToInstance(serviceName,
                                                               properties,
                                                               OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_CONFIDENCE_PROPERTY_NAME,
                                                               confidence,
                                                               methodName);

        if (createdBy != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_CREATED_BY_PROPERTY_NAME,
                                                                      createdBy,
                                                                      methodName);
        }

        if (steward != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_STEWARD_PROPERTY_NAME,
                                                                      steward,
                                                                      methodName);
        }

        if (source != null)
        {
            properties = repositoryHelper.addStringPropertyToInstance(serviceName,
                                                                      properties,
                                                                      OpenMetadataAPIMapper.SEMANTIC_ASSIGNMENT_SOURCE_PROPERTY_NAME,
                                                                      source,
                                                                      methodName);
        }

        this.linkElementToElement(userId,
                                  externalSourceGUID,
                                  externalSourceName,
                                  beanGUID,
                                  beanGUIDParameter,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TYPE_NAME,
                                  glossaryTermGUID,
                                  glossaryTermGUIDParameter,
                                  OpenMetadataAPIMapper.GLOSSARY_TERM_TYPE_NAME,
                                  supportedZones,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_GUID,
                                  OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_NAME,
                                  properties,
                                  methodName);
    }


    /**
     * Remove the relationship between a glossary term and a referenceable (typically
     * a field in the schema).
     *
     * @param userId calling user
     * @param externalSourceGUID guid of the software server capability entity that represented the external source - null for local
     * @param externalSourceName name of the software server capability entity that represented the external source
     * @param beanGUID unique identifier of the referenceable
     * @param glossaryTermGUID unique identifier of the glossary term
     * @param methodName calling method
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws PropertyServerException problem accessing property server
     * @throws UserNotAuthorizedException security access problem
     */
    public void  removeSemanticAssignment(String    userId,
                                          String    externalSourceGUID,
                                          String    externalSourceName,
                                          String    beanGUID,
                                          String    glossaryTermGUID,
                                          String    methodName) throws InvalidParameterException,
                                                                       UserNotAuthorizedException,
                                                                       PropertyServerException
    {
        if (beanGUID != null)
        {
            /*
             * The repository helper will validate the types of GUIDs etc
             */
            repositoryHandler.removeRelationshipBetweenEntities(userId,
                                                                externalSourceGUID,
                                                                externalSourceName,
                                                                OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_GUID,
                                                                OpenMetadataAPIMapper.REFERENCEABLE_TO_MEANING_TYPE_NAME,
                                                                beanGUID,
                                                                OpenMetadataAPIMapper.ASSET_TYPE_NAME,
                                                                glossaryTermGUID,
                                                                methodName);
        }
    }



    /**
     * Create the property facet for the vendor properties.
     *
     * @param userId calling user
     * @param referenceableGUID unique identifier of the software server capability
     * @param vendorProperties properties for the vendor
     * @param methodName calling method
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws PropertyServerException problem accessing property server
     * @throws UserNotAuthorizedException security access problem
     */
    void setVendorProperties(String               userId,
                             String               referenceableGUID,
                             Map<String, String>  vendorProperties,
                             String               methodName) throws InvalidParameterException,
                                                                     UserNotAuthorizedException,
                                                                     PropertyServerException
    {
        if (vendorProperties != null)
        {
            invalidParameterHandler.throwMethodNotSupported(userId, serviceName, serverName, methodName);
            // todo
        }
    }


    /**
     * Retrieve the property facet for the vendor properties. It uses the supportedZones supplied with the service.
     *
     * @param userId calling user
     * @param referenceableGUID unique identifier of the metadata element
     * @param qualifiedName qualified name of the metadata element
     * @param methodName calling method
     *
     * @return map of properties
     *
     * @throws InvalidParameterException one of the parameters is null or invalid
     * @throws PropertyServerException problem accessing property server
     * @throws UserNotAuthorizedException security access problem
     */
    public Map<String, String> getVendorProperties(String userId,
                                                   String referenceableGUID,
                                                   String qualifiedName,
                                                   String methodName) throws InvalidParameterException,
                                                                             UserNotAuthorizedException,
                                                                             PropertyServerException
    {
        // todo
        invalidParameterHandler.throwMethodNotSupported(userId, serviceName, serverName, methodName);
        return null;
    }
}
