/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.odpi.openmetadata.accessservices.subjectarea.generated.entities.GovernanceControl;

import java.io.Serializable;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

// omrs
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.*;

// omas
import org.odpi.openmetadata.accessservices.subjectarea.common.Classification;
import org.odpi.openmetadata.accessservices.subjectarea.common.SystemAttributes;
import org.odpi.openmetadata.accessservices.subjectarea.common.Reference;
import org.odpi.openmetadata.accessservices.subjectarea.server.properties.GovernanceActions;
import org.odpi.openmetadata.accessservices.subjectarea.ffdc.SubjectAreaErrorCode;
import org.odpi.openmetadata.accessservices.subjectarea.ffdc.exceptions.*;
import org.odpi.openmetadata.accessservices.subjectarea.generated.enums.*;
import org.odpi.openmetadata.accessservices.subjectarea.common.Classification;

/**
 * GovernanceControl entity in the Subject Area OMAS.
   An implementation of a governance capability.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class  GovernanceControl implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(GovernanceControl.class);
    private static final String className = GovernanceControl.class.getName();
    private SystemAttributes systemAttributes = null;
    List<Classification> classifications = null;

    private Map<String, Object> extraAttributes =null;
    private Map<String, org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification> extraClassifications =null;


    /**
     * Get the system attributes
     * @return
     */
    public SystemAttributes getSystemAttributes() {
        return systemAttributes;
    }

    public void setSystemAttributes(SystemAttributes systemAttributes) {
        this.systemAttributes = systemAttributes;
    }

    // attributes
    public static final String[] PROPERTY_NAMES_SET_VALUES = new String[] {
        "implementationDescription",
        "title",
        "summary",
        "description",
        "scope",
        "status",
        "priority",
        "implications",
        "outcomes",
        "qualifiedName",
        "additionalProperties",

    // Terminate the list
        null
    };
    public static final String[] ATTRIBUTE_NAMES_SET_VALUES = new String[] {
        "implementationDescription",
        "title",
        "summary",
        "description",
        "scope",
        "priority",
        "implications",
        "outcomes",
        "qualifiedName",

     // Terminate the list
        null
    };
    public static final String[] ENUM_NAMES_SET_VALUES = new String[] {
         "status",

         // Terminate the list
          null
    };
    public static final String[] MAP_NAMES_SET_VALUES = new String[] {
         "additionalProperties",

         // Terminate the list
         null
    };
    public static final java.util.Set<String> PROPERTY_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(PROPERTY_NAMES_SET_VALUES)));
    public static final java.util.Set<String> ATTRIBUTE_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(ATTRIBUTE_NAMES_SET_VALUES)));
    public static final java.util.Set<String> ENUM_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(ENUM_NAMES_SET_VALUES)));
    public static final java.util.Set<String> MAP_NAMES_SET = new HashSet(new HashSet<>(Arrays.asList(MAP_NAMES_SET_VALUES)));


    InstanceProperties obtainInstanceProperties() {
        final String methodName = "obtainInstanceProperties";
        if (log.isDebugEnabled()) {
               log.debug("==> Method: " + methodName);
        }
        InstanceProperties instanceProperties = new InstanceProperties();
        EnumPropertyValue enumPropertyValue=null;
        enumPropertyValue = new EnumPropertyValue();
        // current status of this governance definition.
        enumPropertyValue.setOrdinal(status.ordinal());
        enumPropertyValue.setSymbolicName(status.name());
        instanceProperties.setProperty("status",enumPropertyValue);
        MapPropertyValue mapPropertyValue=null;
        // Additional properties for the element.
        mapPropertyValue = new MapPropertyValue();
        PrimitivePropertyValue primitivePropertyValue=null;
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("implementationDescription",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("title",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("summary",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("description",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("scope",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("status",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("priority",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("implications",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("outcomes",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("qualifiedName",primitivePropertyValue);
        primitivePropertyValue = new PrimitivePropertyValue();
        // TODO  description + change null to value
        primitivePropertyValue.setPrimitiveValue(null);
        instanceProperties.setProperty("additionalProperties",primitivePropertyValue);
        if (log.isDebugEnabled()) {
               log.debug("<== Method: " + methodName);
        }
        return instanceProperties;
    }

       private String implementationDescription;
       /**
        * Description of how this governance control should be implemented.
        * @return String
        */
       public String getImplementationDescription() {
           return this.implementationDescription;
       }

       public void setImplementationDescription(Object implementationDescription) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.implementationDescription = (String)implementationDescription;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String title;
       /**
        * Title describing the governance definition.
        * @return String
        */
       public String getTitle() {
           return this.title;
       }

       public void setTitle(Object title) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.title = (String)title;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String summary;
       /**
        * Short summary of the governance definition.
        * @return String
        */
       public String getSummary() {
           return this.summary;
       }

       public void setSummary(Object summary) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.summary = (String)summary;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String description;
       /**
        * Detailed description of the governance definition.
        * @return String
        */
       public String getDescription() {
           return this.description;
       }

       public void setDescription(Object description) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.description = (String)description;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String scope;
       /**
        * Scope of impact for this governance definition.
        * @return String
        */
       public String getScope() {
           return this.scope;
       }

       public void setScope(Object scope) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.scope = (String)scope;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private GovernanceDefinitionStatus status;
       /**
        * Current status of this governance definition.
        * @return GovernanceDefinitionStatus
        */
       public GovernanceDefinitionStatus getStatus() {
           return this.status;
       }

       public void setStatus(Object status) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.status = (GovernanceDefinitionStatus)status;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String priority;
       /**
        * Relative importance of this governance definition compared to its peers.
        * @return String
        */
       public String getPriority() {
           return this.priority;
       }

       public void setPriority(Object priority) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.priority = (String)priority;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private List<String> implications;
       /**
        * Impact on the organization, people and services when adopting the recommendation in this governance definition.
        * @return List<String>
        */
       public List<String> getImplications() {
           return this.implications;
       }

       public void setImplications(Object implications) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.implications = (List<String>)implications;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private List<String> outcomes;
       /**
        * Expected outcomes.
        * @return List<String>
        */
       public List<String> getOutcomes() {
           return this.outcomes;
       }

       public void setOutcomes(Object outcomes) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.outcomes = (List<String>)outcomes;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private String qualifiedName;
       /**
        * Unique identifier for the entity.
        * @return String
        */
       public String getQualifiedName() {
           return this.qualifiedName;
       }

       public void setQualifiedName(Object qualifiedName) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.qualifiedName = (String)qualifiedName;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }
       private Map<String,String> additionalProperties;
       /**
        * Additional properties for the element.
        * @return Map<String,String>
        */
       public Map<String,String> getAdditionalProperties() {
           return this.additionalProperties;
       }

       public void setAdditionalProperties(Object additionalProperties) throws InvalidParameterException {
           // accept an object and cast to the appropriate type.

           final String methodName = "obtainInstanceProperties";
           try {
                 this.additionalProperties = (Map<String,String>)additionalProperties;
           } catch (ClassCastException e) {
                  SubjectAreaErrorCode errorCode    = SubjectAreaErrorCode.SET_ATTRIBUTE_WRONG_TYPE;
                  String errorMessage = errorCode.getErrorMessageId()
                          + errorCode.getFormattedErrorMessage(className,
                          methodName);
                  log.error(errorMessage,e);
                  throw new InvalidParameterException(errorCode.getHTTPErrorCode(),
                          className,
                          methodName,
                          errorMessage,
                          errorCode.getSystemAction(),
                          errorCode.getUserAction());

           }
       }


    public void setExtraAttributes(Map<String, Object> extraAttributes) {
        this.extraAttributes = extraAttributes;
    }

    public void setClassifications(List<Classification> classifications) {
        this.classifications = classifications;
    }

    /**
     * Get the extra attributes - ones that are in addition to the standard types.
     * @return
     */
    public Map<String, Object> getExtraAttributes() {
        return extraAttributes;
    }

     /**
     * Classifications
     * @return
     */
    public List<Classification> getClassifications() {
        return classifications;
    }
    /**
      * Extra classifications are classifications that are not in the open metadata model - we include the OMRS Classifications.
      */
    public Map<String, org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification> getExtraClassifications() {
        return extraClassifications;
    }

    public void setExtraClassifications(Map<String, org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.instances.Classification> extraClassifications) {
        this.extraClassifications = extraClassifications;
    }

    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("GovernanceControl{");
        if (systemAttributes !=null) {
            sb.append("systemAttributes='").append(systemAttributes.toString()).append('\'');
        }
        sb.append("GovernanceControl Attributes{");
    	sb.append("ImplementationDescription=" +this.implementationDescription);
    	sb.append("Title=" +this.title);
    	sb.append("Summary=" +this.summary);
    	sb.append("Description=" +this.description);
    	sb.append("Scope=" +this.scope);
    	sb.append("Status=" +this.status);
    	sb.append("Priority=" +this.priority);
    	sb.append("Implications=" +this.implications);
    	sb.append("Outcomes=" +this.outcomes);
    	sb.append("QualifiedName=" +this.qualifiedName);
    	sb.append("AdditionalProperties=" +this.additionalProperties);

        sb.append('}');
        if (classifications != null) {
        sb.append(", classifications=[");
            for (Classification classification:classifications) {
                sb.append(classification.toString()).append(", ");
            }
            sb.append(" ],");
        }
        sb.append(", extraAttributes=[");
        if (extraAttributes !=null) {
            for (String attrname: extraAttributes.keySet()) {
                sb.append(attrname).append(":");
                sb.append(extraAttributes.get(attrname)).append(", ");
            }
        }
        sb.append(" ]");

        sb.append('}');

        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        if (!super.equals(o)) { return false; }

        GovernanceControl that = (GovernanceControl) o;
        if (this.implementationDescription != null && !Objects.equals(this.implementationDescription,that.getImplementationDescription())) {
             return false;
        }
        if (this.title != null && !Objects.equals(this.title,that.getTitle())) {
             return false;
        }
        if (this.summary != null && !Objects.equals(this.summary,that.getSummary())) {
             return false;
        }
        if (this.description != null && !Objects.equals(this.description,that.getDescription())) {
             return false;
        }
        if (this.scope != null && !Objects.equals(this.scope,that.getScope())) {
             return false;
        }
        if (this.status != null && !Objects.equals(this.status,that.getStatus())) {
             return false;
        }
        if (this.priority != null && !Objects.equals(this.priority,that.getPriority())) {
             return false;
        }
        if (this.implications != null && !Objects.equals(this.implications,that.getImplications())) {
             return false;
        }
        if (this.outcomes != null && !Objects.equals(this.outcomes,that.getOutcomes())) {
             return false;
        }
        if (this.qualifiedName != null && !Objects.equals(this.qualifiedName,that.getQualifiedName())) {
             return false;
        }
        if (this.additionalProperties != null && !Objects.equals(this.additionalProperties,that.getAdditionalProperties())) {
             return false;
        }

        // We view governanceControls as logically equal by checking the properties that the OMAS knows about - i.e. without accounting for extra attributes and references from the org.odpi.openmetadata.accessservices.subjectarea.server.
        return Objects.equals(systemAttributes, that.systemAttributes) &&
                Objects.equals(classifications, that.classifications) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
         systemAttributes.hashCode(),
         classifications.hashCode()
          , this.implementationDescription
          , this.title
          , this.summary
          , this.description
          , this.scope
          , this.status
          , this.priority
          , this.implications
          , this.outcomes
          , this.qualifiedName
          , this.additionalProperties
        );
    }

    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
}
