/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.opentypes;


import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveBuilder;
import org.odpi.openmetadata.repositoryservices.archiveutilities.OMRSArchiveHelper;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchive;
import org.odpi.openmetadata.repositoryservices.connectors.stores.archivestore.properties.OpenMetadataArchiveType;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.*;
import org.odpi.openmetadata.repositoryservices.ffdc.OMRSErrorCode;
import org.odpi.openmetadata.repositoryservices.ffdc.exception.OMRSLogicErrorException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OpenMetadataTypesArchive builds an open metadata archive containing all of the standard open metadata types.
 * These types have hardcoded dates and guids so that however many times this archive is rebuilt, it will
 * produce the same content.
 * <p>
 * Details of the open metadata types are documented on the wiki:
 * <a href="https://egeria.odpi.org/open-metadata-publication/website/open-metadata-types/">The Open Metadata Type System</a>
 * </p>
 * <p>
 * There are 8 areas, each covering a different topic area of metadata.  The module breaks down the process of creating
 * the models into the areas and then the individual models to simplify the maintenance of this class
 * </p>
 */
public class OpenMetadataTypesArchive
{
    /*
     * This is the header information for the archive.
     */
    private static final String                  archiveGUID        = "bce3b0a0-662a-4f87-b8dc-844078a11a6e";
    private static final String                  archiveName        = "Open Metadata Types";
    private static final String                  archiveDescription = "Standard types for open metadata repositories.";
    private static final OpenMetadataArchiveType archiveType        = OpenMetadataArchiveType.CONTENT_PACK;
    private static final String                  archiveVersion     = "2.11";
    private static final String                  originatorName     = "Egeria";
    private static final String                  originatorLicense  = "Apache 2.0";
    private static final Date                    creationDate       = new Date(1588261366992L);

    /*
     * Specific values for initializing TypeDefs
     */
    private static final long   versionNumber = 1L;
    private static final String versionName   = "1.0";


    private OMRSArchiveBuilder archiveBuilder;
    private OMRSArchiveHelper  archiveHelper;

    /**
     * Default constructor sets up the archive builder.  This in turn sets up the header for the archive.
     */
    public OpenMetadataTypesArchive()
    {
        this.archiveBuilder = new OMRSArchiveBuilder(archiveGUID,
                                                     archiveName,
                                                     archiveDescription,
                                                     archiveType,
                                                     archiveVersion,
                                                     originatorName,
                                                     originatorLicense,
                                                     creationDate,
                                                     null);

        this.archiveHelper = new OMRSArchiveHelper(archiveBuilder,
                                                   archiveGUID,
                                                   originatorName,
                                                   creationDate,
                                                   versionNumber,
                                                   versionName);
    }


    /**
     * Chained constructor sets up the archive builder.  This in turn sets up the header for the archive.
     *
     * @param archiveBuilder accumulator for types
     */
    public OpenMetadataTypesArchive(OMRSArchiveBuilder archiveBuilder)
    {
        this.archiveBuilder = archiveBuilder;

        this.archiveHelper = new OMRSArchiveHelper(archiveBuilder,
                                                   archiveGUID,
                                                   originatorName,
                                                   creationDate,
                                                   versionNumber,
                                                   versionName);
    }


    /**
     * Return the unique identifier for this archive.
     *
     * @return String guid
     */
    public String getArchiveGUID()
    {
        return archiveGUID;
    }


    /**
     * Returns the open metadata type archive containing all of the standard open metadata types.
     *
     * @return populated open metadata archive object
     */
    public OpenMetadataArchive getOpenMetadataArchive()
    {
        final String methodName = "getOpenMetadataArchive";

        if (this.archiveBuilder != null)
        {
            /*
             * Build the type archive.
             */
            this.getOriginalTypes();

            /*
             * The completed archive is ready to be packaged up and returned
             */
            return this.archiveBuilder.getOpenMetadataArchive();
        }
        else
        {
            /*
             * This is a logic error since it means the creation of the archive builder threw an exception
             * in the constructor and so this object should not be used.
             */
            throw new OMRSLogicErrorException(OMRSErrorCode.ARCHIVE_UNAVAILABLE.getMessageDefinition(),
                                              this.getClass().getName(),
                                              methodName);
        }
    }


    /**
     * Add the types from this archive to the archive builder supplied in the
     * constructor.
     */
    public void getOriginalTypes()
    {
        OpenMetadataTypesArchive2_10 previousTypes = new OpenMetadataTypesArchive2_10(archiveBuilder);

        /*
         * Pull the types from previous releases.
         */
        previousTypes.getOriginalTypes();

        /*
         * Calls for new and changed types go here
         */
        update0010BaseModel();
        update0050ApplicationsAndProcesses();
        update04xxGovernanceDefinitions();
        update0530TabularSchema();
        update0531DocumentSchema();
        update0534RelationalSchema();
        update0535EventSchemas();
        update0536APISchemas();
    }

    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * Mark deprecated field in Asset
     */
    private void update0010BaseModel()
    {
        this.archiveBuilder.addTypeDefPatch(updateAsset());
    }

    /**
     * Deprecate the ownership properties - use Ownership classification instead
     *
     * @return patch
     */
    private TypeDefPatch updateAsset()
    {
        /*
         * Create the Patch
         */
        final String typeName = "Asset";

        TypeDefPatch  typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute1Name            = "owner";
        final String attribute1Description     = "Deprecated Attribute. Person, team or engine responsible for this type of action. Use Ownership classification";
        final String attribute1DescriptionGUID = null;
        final String attribute2Name            = "ownerType";
        final String attribute2Description     = "Deprecated Attribute. Type of element representing the owner. Use Ownership classification";
        final String attribute2DescriptionGUID = null;
        final String attribute3Name            = "zoneMembership";
        final String attribute3Description     = "Deprecated Attribute. The list of zones that this asset belongs to. Use AssetZoneMembership classification";
        final String attribute3DescriptionGUID = null;
        final String attribute4Name            = "latestChange";
        final String attribute4Description     = "Deprecated Attribute. Description of the last change to the asset's metadata. Use LatestChange classification";
        final String attribute4DescriptionGUID = null;

        property = archiveHelper.getStringTypeDefAttribute(attribute1Name,
                                                           attribute1Description,
                                                           attribute1DescriptionGUID);
        property.setAttributeStatus(TypeDefAttributeStatus.DEPRECATED_ATTRIBUTE);
        properties.add(property);
        property = archiveHelper.getEnumTypeDefAttribute("AssetOwnerType",
                                                         attribute2Name,
                                                         attribute2Description,
                                                         attribute2DescriptionGUID);
        property.setAttributeStatus(TypeDefAttributeStatus.DEPRECATED_ATTRIBUTE);
        properties.add(property);
        property = archiveHelper.getArrayStringTypeDefAttribute(attribute3Name,
                                                                attribute3Description,
                                                                attribute3DescriptionGUID);
        property.setAttributeStatus(TypeDefAttributeStatus.DEPRECATED_ATTRIBUTE);
        properties.add(property);
        property = archiveHelper.getStringTypeDefAttribute(attribute4Name,
                                                           attribute4Description,
                                                           attribute4DescriptionGUID);
        property.setAttributeStatus(TypeDefAttributeStatus.DEPRECATED_ATTRIBUTE);
        properties.add(property);


        typeDefPatch.setPropertyDefinitions(properties);
        return typeDefPatch;
    }



    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * Add new data managers
     */
    private void update0050ApplicationsAndProcesses()
    {

        this.archiveBuilder.addEntityDef(addAPIManagerEntity());
        this.archiveBuilder.addEntityDef(addEventBrokerEntity());
    }



    /**
     * This new subtype of software server capability for API managers.
     *
     * @return entity definition
     */
    private EntityDef addAPIManagerEntity()
    {
        final String guid            = "283a127d-3acd-4d64-b558-1fce9db9a35b";
        final String name            = "APIManager";
        final String description     = "A capability that manages callable APIs.";
        final String descriptionGUID = null;

        final String superTypeName = "SoftwareServerCapability";

        return archiveHelper.getDefaultEntityDef(guid,
                                                 name,
                                                 this.archiveBuilder.getEntityDef(superTypeName),
                                                 description,
                                                 descriptionGUID);

    }



    /**
     * This new subtype of software server capability for Event Brokers.
     *
     * @return entity definition
     */
    private EntityDef addEventBrokerEntity()
    {
        final String guid            = "309dfc3c-663b-4732-957b-e4a084436314";
        final String name            = "EventBroker";
        final String description     = "A capability that supports event-based services, typically around topics.";
        final String descriptionGUID = null;

        final String superTypeName = "SoftwareServerCapability";

        return archiveHelper.getDefaultEntityDef(guid,
                                                 name,
                                                 this.archiveBuilder.getEntityDef(superTypeName),
                                                 description,
                                                 descriptionGUID);

    }


    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * A variety of changes to improve consistency and flexibility of the governance definitions
     */
    private void update04xxGovernanceDefinitions()
    {

        this.archiveBuilder.addEntityDef(addThreatEntity());

        this.archiveBuilder.addRelationshipDef(addGovernanceDefinitionScopeRelationship());

    }


    /**
     * This relationships allows the scope of a governance definition to be set up.
     *
     * @return  relationship definition
     */
    private RelationshipDef addGovernanceDefinitionScopeRelationship()
    {
        final String guid            = "3845b5cc-8c85-462f-b7e6-47472a568793";
        final String name            = "GovernanceDefinitionScope";
        final String description     = "Link between a scope - such as a digital service, infrastructure element or organization - and a governance definition.";
        final String descriptionGUID = null;

        final ClassificationPropagationRule classificationPropagationRule = ClassificationPropagationRule.NONE;

        RelationshipDef relationshipDef = archiveHelper.getBasicRelationshipDef(guid,
                                                                                name,
                                                                                null,
                                                                                description,
                                                                                descriptionGUID,
                                                                                classificationPropagationRule);

        RelationshipEndDef relationshipEndDef;

        /*
         * Set up end 1.
         */
        final String                     end1EntityType               = "Referenceable";
        final String                     end1AttributeName            = "definitionAppliesTo";
        final String                     end1AttributeDescription     = "Elements defining the scope that the governance definition applies to.";
        final String                     end1AttributeDescriptionGUID = null;
        final RelationshipEndCardinality end1Cardinality              = RelationshipEndCardinality.ANY_NUMBER;

        relationshipEndDef = archiveHelper.getRelationshipEndDef(this.archiveBuilder.getEntityDef(end1EntityType),
                                                                 end1AttributeName,
                                                                 end1AttributeDescription,
                                                                 end1AttributeDescriptionGUID,
                                                                 end1Cardinality);
        relationshipDef.setEndDef1(relationshipEndDef);


        /*
         * Set up end 2.
         */
        final String                     end2EntityType               = "GovernanceDefinition";
        final String                     end2AttributeName            = "associatedGovernanceDefinitions";
        final String                     end2AttributeDescription     = "Governance definitions for this scope.";
        final String                     end2AttributeDescriptionGUID = null;
        final RelationshipEndCardinality end2Cardinality              = RelationshipEndCardinality.ANY_NUMBER;

        relationshipEndDef = archiveHelper.getRelationshipEndDef(this.archiveBuilder.getEntityDef(end2EntityType),
                                                                 end2AttributeName,
                                                                 end2AttributeDescription,
                                                                 end2AttributeDescriptionGUID,
                                                                 end2Cardinality);
        relationshipDef.setEndDef2(relationshipEndDef);

        return relationshipDef;
    }



    /**
     * This new subtype of governance driver is to document a specific threat.
     *
     * @return entity definition
     */
    private EntityDef addThreatEntity()
    {
        final String guid            = "4ca51fdf-9b70-46b1-bdf6-8860429e78d8";
        final String name            = "Threat";
        final String description     = "A description of a specific threat.";
        final String descriptionGUID = null;

        final String superTypeName = "GovernanceDriver";

        return archiveHelper.getDefaultEntityDef(guid,
                                                 name,
                                                 this.archiveBuilder.getEntityDef(superTypeName),
                                                 description,
                                                 descriptionGUID);

    }


    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * The TabularColumnType only allows for a column to be primitive - could be a literal.
     */
    private void update0530TabularSchema()
    {
        this.archiveBuilder.addTypeDefPatch(deprecateTabularColumnType());
    }

    private TypeDefPatch deprecateTabularColumnType()
    {
        final String typeName = "TabularColumnType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setTypeDefStatus(TypeDefStatus.DEPRECATED_TYPEDEF);

        return typeDefPatch;
    }



    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * Deprecate the specialist SchemaTypes for documents since the offer little value.
     */
    private void update0531DocumentSchema()
    {
        this.archiveBuilder.addTypeDefPatch(deprecateSimpleDocumentType());
        this.archiveBuilder.addTypeDefPatch(deprecateStructDocumentType());
        this.archiveBuilder.addTypeDefPatch(deprecateMapDocumentType());
    }

    private TypeDefPatch deprecateSimpleDocumentType()
    {
        final String typeName = "SimpleDocumentType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setTypeDefStatus(TypeDefStatus.DEPRECATED_TYPEDEF);

        return typeDefPatch;
    }


    private TypeDefPatch deprecateStructDocumentType()
    {
        final String typeName = "StructDocumentType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setTypeDefStatus(TypeDefStatus.DEPRECATED_TYPEDEF);

        return typeDefPatch;
    }


    private TypeDefPatch deprecateMapDocumentType()
    {
        final String typeName = "MapDocumentType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setTypeDefStatus(TypeDefStatus.DEPRECATED_TYPEDEF);

        return typeDefPatch;
    }



    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * Change superclass of RelationshipTableType to be ComplexSchemaType
     */
    private void update0534RelationalSchema()
    {
        this.archiveBuilder.addTypeDefPatch(updateRelationalTableTypeEntity());
    }


    private TypeDefPatch updateRelationalTableTypeEntity()
    {
        final String typeName = "RelationalTableType";

        final String superTypeName = "ComplexSchemaType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setSuperType(this.archiveBuilder.getEntityDef(superTypeName));

        return typeDefPatch;
    }



    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * A variety of changes to improve definition of EventType schemas
     */
    private void update0535EventSchemas()
    {
        this.archiveBuilder.addEntityDef(addEventTypeListEntity());
        this.archiveBuilder.addEntityDef(addEventSchemaAttributeEntity());
    }


    /**
     * This new subtype of schema type choice for events to make searching easier.
     *
     * @return entity definition
     */
    private EntityDef addEventTypeListEntity()
    {
        final String guid            = "77ccda3d-c4c6-464c-a424-4b2cb27ac06c";
        final String name            = "EventTypeList";
        final String description     = "A list of event types that flow on a topic.";
        final String descriptionGUID = null;

        final String superTypeName = "SchemaTypeChoice";

        return archiveHelper.getDefaultEntityDef(guid,
                                                 name,
                                                 this.archiveBuilder.getEntityDef(superTypeName),
                                                 description,
                                                 descriptionGUID);

    }


    /**
     * This new subtype of schema attribute for events to make searching easier.
     *
     * @return entity definition
     */
    private EntityDef addEventSchemaAttributeEntity()
    {
        final String guid            = "5be4ee8f-4d0c-45cd-a411-22a468950342";
        final String name            = "EventSchemaAttribute";
        final String description     = "A data field in an event type.";
        final String descriptionGUID = null;

        final String superTypeName = "SchemaAttribute";

        return archiveHelper.getDefaultEntityDef(guid,
                                                 name,
                                                 this.archiveBuilder.getEntityDef(superTypeName),
                                                 description,
                                                 descriptionGUID);

    }


    /*
     * -------------------------------------------------------------------------------------------------------
     */


    /**
     * A variety of changes to improve definition of API schemas
     */
    private void update0536APISchemas()
    {

        this.archiveBuilder.addEntityDef(addAPIParameterListEntity());
        this.archiveBuilder.addEntityDef(addAPIParameterEntity());

        this.archiveBuilder.addTypeDefPatch(updateAPIOperation());
    }



    /**
     * This new subtype of schema type that describes a list of parameters for an API.
     *
     * @return entity definition
     */
    private EntityDef addAPIParameterListEntity()
    {
        final String guid            = "ba167b12-969f-49d3-8bea-d04228d9a44b";
        final String name            = "APIParameterList";
        final String description     = "A list of parameters for an API.";
        final String descriptionGUID = null;

        final String superTypeName = "ComplexSchemaType";

        EntityDef entityDef = archiveHelper.getDefaultEntityDef(guid,
                                                                name,
                                                                this.archiveBuilder.getEntityDef(superTypeName),
                                                                description,
                                                                descriptionGUID);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute2Name            = "required";
        final String attribute2Description     = "Is this parameter list required when calling the API.";
        final String attribute2DescriptionGUID = null;

        property = archiveHelper.getBooleanTypeDefAttribute(attribute2Name,
                                                            attribute2Description,
                                                            attribute2DescriptionGUID);
        properties.add(property);

        entityDef.setPropertiesDefinition(properties);

        return entityDef;
    }




    /**
     * This new subtype of SchemaAttribute is used for describing a data value that is part of a API definition.
     *
     * @return entity definition
     */
    private EntityDef addAPIParameterEntity()
    {
        final String guid            = "10277b13-509c-480e-9829-bc16d0eafc53";
        final String name            = "APIParameter";
        final String description     = "A data value that is part of a API definition.";
        final String descriptionGUID = null;

        final String superTypeName = "SchemaAttribute";

        EntityDef entityDef = archiveHelper.getDefaultEntityDef(guid,
                                                                name,
                                                                this.archiveBuilder.getEntityDef(superTypeName),
                                                                description,
                                                                descriptionGUID);

        /*
         * Build the attributes
         */
        List<TypeDefAttribute> properties = new ArrayList<>();
        TypeDefAttribute       property;

        final String attribute3Name            = "parameterType";
        final String attribute3Description     = "What type of parameter is it";
        final String attribute3DescriptionGUID = null;

        property = archiveHelper.getStringTypeDefAttribute(attribute3Name,
                                                           attribute3Description,
                                                           attribute3DescriptionGUID);
        properties.add(property);


        entityDef.setPropertiesDefinition(properties);

        return entityDef;
    }


    /**
     * This change means that APIOperation inherits from ComplexSchemaType rather than SchemaType.
     *
     * @return patched type
     */
    private TypeDefPatch updateAPIOperation()
    {
        /*
         * Create the Patch
         */
        final String typeName = "APIOperation";

        final String superTypeName = "ComplexSchemaType";

        TypeDefPatch typeDefPatch = archiveBuilder.getPatchForType(typeName);

        typeDefPatch.setUpdatedBy(originatorName);
        typeDefPatch.setUpdateTime(creationDate);
        typeDefPatch.setSuperType(this.archiveBuilder.getEntityDef(superTypeName));

        return typeDefPatch;
    }
}

