/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.odpi.openmetadata.accessservices.subjectarea.generated.entities.ExternalId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

// omrs
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.*;
import org.odpi.openmetadata.repositoryservices.archivemanager.OMRSArchiveAccessor;

// omas
import org.odpi.openmetadata.accessservices.subjectarea.common.*;
import org.odpi.openmetadata.accessservices.subjectarea.generated.enums.*;
import org.odpi.openmetadata.accessservices.subjectarea.generated.classifications.ClassificationFactory;
import org.odpi.openmetadata.accessservices.subjectarea.generated.classifications.Confidence.Confidence;
import org.odpi.openmetadata.accessservices.subjectarea.generated.classifications.Confidentiality.Confidentiality;
import org.odpi.openmetadata.accessservices.subjectarea.generated.classifications.Criticality.Criticality;
import org.odpi.openmetadata.accessservices.subjectarea.generated.classifications.Retention.Retention;
import org.odpi.openmetadata.accessservices.subjectarea.server.properties.GovernanceActions;
import org.odpi.openmetadata.accessservices.subjectarea.ffdc.exceptions.InvalidParameterException;

/**
 * Static mapping methods to map between ExternalId and the omrs equivalents.
 */
public class ExternalIdMapper {
    private static final Logger log = LoggerFactory.getLogger( ExternalIdMapper.class);
    private static final String className = ExternalIdMapper.class.getName();
   /**
    * @param - omrsEntityDetail - the supplied EntityDetail
    * @return - equivalent ExternalId
    */
   static public org.odpi.openmetadata.accessservices.subjectarea.generated.entities.ExternalId.ExternalId mapOmrsEntityDetailToExternalId(EntityDetail omrsEntityDetail) throws InvalidParameterException{
        String entityTypeName = omrsEntityDetail.getType().getTypeDefName();
        if ("ExternalId".equals(entityTypeName)) {
                org.odpi.openmetadata.accessservices.subjectarea.generated.entities.ExternalId.ExternalId externalId = new ExternalId();
                //set core attributes
                SystemAttributes systemAttributes = new SystemAttributes();

                InstanceStatus instanceStatus =  omrsEntityDetail.getStatus();
                Status omas_status = SubjectAreaUtils.convertInstanceStatusToStatus(instanceStatus);
                systemAttributes.setStatus(omas_status);

                systemAttributes.setCreatedBy(omrsEntityDetail.getCreatedBy());
                systemAttributes.setUpdatedBy(omrsEntityDetail.getUpdatedBy());
                systemAttributes.setCreateTime(omrsEntityDetail.getCreateTime());
                systemAttributes.setUpdateTime(omrsEntityDetail.getUpdateTime());
                systemAttributes.setVersion(omrsEntityDetail.getVersion());
                systemAttributes.setGUID(omrsEntityDetail.getGUID());
                externalId.setSystemAttributes(systemAttributes);


                // Set properties
                InstanceProperties omrsEntityDetailProperties = omrsEntityDetail.getProperties();
                Iterator omrsPropertyIterator = omrsEntityDetailProperties.getPropertyNames();
                while (omrsPropertyIterator.hasNext()) {
                    String name = (String) omrsPropertyIterator.next();
                    //TODO check if this is a property we expect or whether the type has been added to.
                    // this is a property we expect
                    InstancePropertyValue value = omrsEntityDetailProperties.getPropertyValue(name);

                    // supplied guid matches the expected type

                    Object actualValue;
                    switch (value.getInstancePropertyCategory()) {
                        case PRIMITIVE:
                            PrimitivePropertyValue primitivePropertyValue = (PrimitivePropertyValue) value;
                            actualValue = primitivePropertyValue.getPrimitiveValue();
                            if (externalId.ATTRIBUTE_NAMES_SET.contains(name)) {
                               if (name.equals("identifier")) {
                                   externalId.setIdentifier(actualValue);
                               }
                               if (name.equals("qualifiedName")) {
                                   externalId.setQualifiedName(actualValue);
                               }
                            } else {
                                // put out the omrs value object
                                if (null==externalId.getExtraAttributes())  {
                                     externalId.setExtraAttributes(new HashMap<String, Object>());
                                }
                               externalId.getExtraAttributes().put(name, primitivePropertyValue);
                            }
                            break;
                        case ENUM:
                            EnumPropertyValue enumPropertyValue = (EnumPropertyValue) value;
                            String symbolicName = enumPropertyValue.getSymbolicName();
                            if (externalId.ENUM_NAMES_SET.contains(name)) {
                                 if (name.equals("keyPattern")) {
                                       org.odpi.openmetadata.accessservices.subjectarea.generated.enums.KeyPattern keyPattern = org.odpi.openmetadata.accessservices.subjectarea.generated.enums.KeyPattern.valueOf(symbolicName);
                                      externalId.setKeyPattern(keyPattern);
                                 }
                            } else {
                                // put out the omrs value object
                                if (null==externalId.getExtraAttributes())  {
                                     externalId.setExtraAttributes(new HashMap<String, Object>());
                                }
                                 externalId.getExtraAttributes().put(name, enumPropertyValue);
                             }

                            break;
                        case MAP:
                            if (externalId.MAP_NAMES_SET.contains(name)) {
                                 MapPropertyValue mapPropertyValue = (MapPropertyValue) value;
                                 InstanceProperties instancePropertyForMap = (InstanceProperties) mapPropertyValue.getMapValues();

                                 if (name.equals("additionalProperties")) {

                                       // Only support Map<String,String> as that is what is in the archive types at this time.
                                       Map<String, String> actualMap = new HashMap();
                                       Iterator iter = instancePropertyForMap.getPropertyNames();
                                       while (iter.hasNext()) {
                                           String mapkey = (String) iter.next();
                                           PrimitivePropertyValue primitivePropertyMapValue = (PrimitivePropertyValue) instancePropertyForMap.getPropertyValue(mapkey);
                                           String mapvalue = (String) primitivePropertyMapValue.getPrimitiveValue();
                                           actualMap.put(mapkey, mapvalue);
                                       }
                                       externalId.setAdditionalProperties(actualMap);
                                 }
                               }
                               break;
                        case ARRAY:
                        case STRUCT:
                        case UNKNOWN:
                            // error
                            break;
                    }

                }   // end while

                 // set classifications
                 List<org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification> omrsclassifications = omrsEntityDetail.getClassifications() ;
                 if (omrsclassifications != null && !omrsclassifications.isEmpty()){
                    for (org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification omrsClassification:omrsclassifications) {
                        String omrsClassificationName = omrsClassification.getName();
                        org.odpi.openmetadata.accessservices.subjectarea.common.Classification omasClassification = ClassificationFactory.getClassification(omrsClassificationName);
                        if (omasClassification !=null) {
                            // this is a classification we know about.
                            while (omrsClassification.getProperties().getPropertyNames().hasNext()) {
                                String omrsAttrName = omrsClassification.getProperties().getPropertyNames().next();
                                InstancePropertyValue instancePropertyValue = omrsClassification.getProperties().getPropertyValue(omrsAttrName);
                                omasClassification.obtainInstanceProperties().setProperty(omrsAttrName,instancePropertyValue);
                                externalId.classifications.add(omasClassification);
                            }
                        } else {
                            if (null==externalId.getExtraClassifications())  {
                                 externalId.setExtraClassifications(new HashMap<String, org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification>());
                            }
                            externalId.getExtraClassifications().put(omrsClassificationName,omrsClassification);
                        }
                    }
                 }
                 return externalId;
            } else {
                // TODO wrong entity type for this guid
            }
            return null;
    }
    /**
     * Map (convert) the supplied ExternalId to an entityDetail.
     * @param -  externalId  supplied ExternalId
     * @return -  entityDetail equivalent to externalId
     */
    static public EntityDetail mapExternalIdToOmrsEntityDetail(org.odpi.openmetadata.accessservices.subjectarea.generated.entities.ExternalId.ExternalId externalId) {
            EntityDetail omrsEntityDetail = new EntityDetail();
            SystemAttributes systemAttributes = externalId.getSystemAttributes();
            if (systemAttributes!=null) {
                   if (systemAttributes.getCreatedBy()!=null)
                        omrsEntityDetail.setCreatedBy(systemAttributes.getCreatedBy());
                   if (systemAttributes.getUpdatedBy()!=null)
                        omrsEntityDetail.setUpdatedBy(systemAttributes.getUpdatedBy());
                   if (systemAttributes.getCreateTime()!=null)
                        omrsEntityDetail.setCreateTime(systemAttributes.getCreateTime());
                   if (systemAttributes.getUpdateTime()!=null)
                        omrsEntityDetail.setUpdateTime(systemAttributes.getUpdateTime());
                   if (systemAttributes.getVersion()!=null)
                        omrsEntityDetail.setVersion(systemAttributes.getVersion());
                   if (systemAttributes.getStatus()!=null) {
                        InstanceStatus instanceStatus = SubjectAreaUtils.convertStatusToStatusInstance(systemAttributes.getStatus());
                        omrsEntityDetail.setStatus(instanceStatus);
                   }
            }

            InstanceProperties instanceProperties = new InstanceProperties();
            // primitives

            if (externalId.getIdentifier()!=null) {
                PrimitivePropertyValue primitivePropertyValue = new PrimitivePropertyValue();
                primitivePropertyValue.setPrimitiveDefCategory(PrimitiveDefCategory.OM_PRIMITIVE_TYPE_STRING);
                primitivePropertyValue.setPrimitiveValue(externalId.getIdentifier());
                instanceProperties.setProperty("identifier", primitivePropertyValue);
            }
            if (externalId.getQualifiedName()!=null) {
                PrimitivePropertyValue primitivePropertyValue = new PrimitivePropertyValue();
                primitivePropertyValue.setPrimitiveDefCategory(PrimitiveDefCategory.OM_PRIMITIVE_TYPE_STRING);
                primitivePropertyValue.setPrimitiveValue(externalId.getQualifiedName());
                instanceProperties.setProperty("qualifiedName", primitivePropertyValue);
            }
            if (externalId.getKeyPattern()!=null) {
                KeyPattern enumType = externalId.getKeyPattern();
                EnumPropertyValue enumPropertyValue = new EnumPropertyValue();
                enumPropertyValue.setOrdinal(enumType.ordinal());
                enumPropertyValue.setSymbolicName(enumType.name());
                instanceProperties.setProperty("keyPattern", enumPropertyValue);
            }
            if (externalId.getAdditionalProperties()!=null) {

                Map<String,String> map =externalId.getAdditionalProperties();
                MapPropertyValue mapPropertyValue = new MapPropertyValue();

                for (String key:map.keySet()) {
                   PrimitivePropertyValue primitivePropertyValue = new PrimitivePropertyValue();
                   primitivePropertyValue.setPrimitiveDefCategory(PrimitiveDefCategory.OM_PRIMITIVE_TYPE_STRING);
                   primitivePropertyValue.setPrimitiveValue(map.get(key));
                   mapPropertyValue.setMapValue(key,primitivePropertyValue);
                }

                instanceProperties.setProperty("additionalProperties", mapPropertyValue);
            }
            omrsEntityDetail.setProperties(instanceProperties);
            // set the type in the entity
            OMRSArchiveAccessor archiveAccessor = OMRSArchiveAccessor.getInstance();
            TypeDef typeDef = archiveAccessor.getEntityDefByName("ExternalId");
            InstanceType template = SubjectAreaUtils.createTemplateFromTypeDef(typeDef);
            InstanceType instanceType = new InstanceType(template);
            omrsEntityDetail.setType(instanceType);

            // map the classifications
            populateOmrsEntityWithOmasClassifications(omrsEntityDetail,(List<org.odpi.openmetadata.accessservices.subjectarea.common.Classification>)externalId.getClassifications());
            return omrsEntityDetail;
    }

    private static void populateOmrsEntityWithOmasClassifications(EntityDetail omrsEntityDetail, List<org.odpi.openmetadata.accessservices.subjectarea.common.Classification> omasClassifications) {
        if (omasClassifications!= null && omasClassifications.size()>0) {
            ArrayList<org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification> omrsClassifications = new ArrayList<org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification>();
            for (org.odpi.openmetadata.accessservices.subjectarea.common.Classification omasClassification : omasClassifications) {
                SystemAttributes systemAttributes = omasClassification.getSystemAttributes();
                org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification omrsClassification = new org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification();

                if (systemAttributes != null) {
                    if (systemAttributes.getCreatedBy() != null)
                        omrsClassification.setCreatedBy(systemAttributes.getCreatedBy());
                    if (systemAttributes.getUpdatedBy() != null)
                        omrsClassification.setUpdatedBy(systemAttributes.getUpdatedBy());
                    if (systemAttributes.getCreateTime() != null)
                        omrsClassification.setCreateTime(systemAttributes.getCreateTime());
                    if (systemAttributes.getUpdateTime() != null)
                        omrsClassification.setUpdateTime(systemAttributes.getUpdateTime());
                    if (systemAttributes.getVersion() != null)
                        omrsClassification.setVersion(systemAttributes.getVersion());
                }
                // copy over the other attributes.
                Iterator iter = omasClassification.obtainInstanceProperties().getPropertyNames();
                while (iter.hasNext()) {
                    String name = (String) iter.next();
                    InstancePropertyValue instancePropertyValue = omasClassification.obtainInstanceProperties().getPropertyValue(name);
                    omrsClassification.getProperties().setProperty(name, instancePropertyValue);
                }
                omrsClassifications.add(omrsClassification);
            }
            omrsEntityDetail.setClassifications(omrsClassifications);
        }
    }
}
