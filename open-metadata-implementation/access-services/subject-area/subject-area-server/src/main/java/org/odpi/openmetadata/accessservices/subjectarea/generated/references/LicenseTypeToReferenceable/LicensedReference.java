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
package org.odpi.openmetadata.accessservices.subjectarea.generated.references.LicenseTypeToReferenceable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.odpi.openmetadata.accessservices.subjectarea.common.Reference;
import org.odpi.openmetadata.accessservices.subjectarea.server.properties.line.Line;
import org.odpi.openmetadata.accessservices.subjectarea.generated.entities.Referenceable.Referenceable;


import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

// This is a generated file - do not edit - run ReferenceGenerator to regenerate this file.

/**
 * This is a reference, which is a view of a relationship from the perspective of one of the ends. A relationship is
 * the link between 2 OMAS entities.
 *
 * A reference is used when working with OMAS entity APIs. An OMAS entity has attributes, classifications
 * and references.
 *
 * This Reference is called LicensedReference. It is used in type LicenseType to represent a
 * reference to an OMAS entity of type referenceable. The reference also contains information
 * about the relationship; the relationship guid, name, relationship attributes and a list of unique attributes.
 * Relationship attributes are attributes of the relationship.
 *
 * It is possible to work with the relationship itself using the OMAS API using the relationship guid
 * contained in this reference.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class LicensedReference extends Reference implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(LicensedReference.class);
    private static final String className = LicensedReference.class.getName();

    protected String relationship_Type = "License";
    protected String name = "licensed";
    protected Referenceable referenceable =null;



    public Referenceable getReferenceable() {
        return  referenceable;
    }
    public void setReferenceable(Referenceable referenceable) {
        this.referenceable = referenceable;
    }
    private String licenseGUID;
    /**
     * Unique identifier of the actual license.
     * @return
     */
    public String getLicenseGUID() {
        return this.licenseGUID;
    }

    public void setLicenseGUID(String licenseGUID) {
        this.licenseGUID = licenseGUID;
    }
    private String start;
    /**
     * Start date for the license.
     * @return
     */
    public String getStart() {
        return this.start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    private String end;
    /**
     * End date for the license.
     * @return
     */
    public String getEnd() {
        return this.end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    private String conditions;
    /**
     * Any special conditions or endorsements over the basic license type.
     * @return
     */
    public String getConditions() {
        return this.conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    private String licensedBy;
    /**
     * Person or organization that owns the intellectual property.
     * @return
     */
    public String getLicensedBy() {
        return this.licensedBy;
    }

    public void setLicensedBy(String licensedBy) {
        this.licensedBy = licensedBy;
    }
    private String custodian;
    /**
     * The person, engine or organization tht will ensure the license is honored.
     * @return
     */
    public String getCustodian() {
        return this.custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }
    private String licensee;
    /**
     * The person or organization that holds the license.
     * @return
     */
    public String getLicensee() {
        return this.licensee;
    }

    public void setLicensee(String licensee) {
        this.licensee = licensee;
    }
    private String notes;
    /**
     * Additional notes about the license.
     * @return
     */
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public LicensedReference() {
        this(null, null, (Map<String, Object>) null);
    }

    public LicensedReference(String guid) {
        this(guid, null, (Map<String, Object>) null);
    }

    public LicensedReference(String guid, String relatedEndType) {
        this(guid, relatedEndType, (Map<String, Object>) null);
    }

    public LicensedReference(String relatedEndType, Map<String, Object> uniqueAttributes) {
        this(null, relatedEndType, uniqueAttributes);
    }

    public LicensedReference(String relatedEndType, final String attrName, final Object attrValue) {
        this(null, relatedEndType, new HashMap<String, Object>() {{
            put(attrName, attrValue);
        }});
    }

    public LicensedReference(String guid, String relatedEndType, Map<String, Object> uniqueAttributes) {
        setRelationshipGuid(guid);
        setRelatedEndType(relatedEndType);
        setUniqueAttributes(uniqueAttributes);
    }

    public LicensedReference(Reference other) {
        if (other != null) {
            setRelationshipGuid(other.getRelationshipGuid());
            setRelatedEndGuid(other.getRelatedEndGuid());
            setRelatedEndType(other.getRelatedEndType());
            setUniqueAttributes(other.getUniqueAttributes());
        }
    }
    
   /**
     * Populate the reference from a relationship
     * @param entityGuid
     * @param Line
     */
    public LicensedReference(String entityGuid, Line line) {
        setRelationshipGuid(line.getGuid());
        if (entityGuid.equals(line.getEntity1Guid())) {
            setRelatedEndGuid(line.getEntity2Guid());
            setRelatedEndType(line.getEntity2Type());
            // TODO UniqueAttributes
            //setUniqueAttributes(line.get
        } else {
            setRelatedEndGuid(line.getEntity1Guid());
            setRelatedEndType(line.getEntity1Type());
            // TODO UniqueAttributes
            //setUniqueAttributes(line.get
        }
    }    

    public LicensedReference(Map objIdMap) {
        if (objIdMap != null) {
            Object reg = objIdMap.get(KEY_RELATED_END_GUID);
            Object rg = objIdMap.get(KEY_RELATIONSHIP_GUID);
            Object t = objIdMap.get(KEY_TYPENAME);
            Object u = objIdMap.get(KEY_UNIQUE_ATTRIBUTES);

            if (reg != null) {
                setRelatedEndGuid(reg.toString());
            }
            if (rg != null) {
                setRelationshipGuid(rg.toString());
            }


            if (t != null) {
                setRelatedEndType(t.toString());
            }

            if (u != null && u instanceof Map) {
                setUniqueAttributes((Map) u);
            }
        }
    }

    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }

        sb.append("Reference{");
        sb.append("relatedEndGuid='").append(getRelatedEndGuid()).append('\'');
        sb.append("relationshipGuid='").append(getRelationshipGuid()).append('\'');
        sb.append("relatedEndType='").append(getRelatedEndType()).append('\'');
        sb.append(", uniqueAttributes={");
//  AtlasBaseTypeDef.dumpObjects(uniqueAttributes, sb);
        sb.append("}");
 	sb.append("{");
	sb.append("this.licenseGUID ");
	sb.append("this.start ");
	sb.append("this.end ");
	sb.append("this.conditions ");
	sb.append("this.licensedBy ");
	sb.append("this.custodian ");
	sb.append("this.licensee ");
	sb.append("this.notes ");
 	sb.append('}');


        sb.append('}');

        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reference that = (Reference) o;

        if (relatedEndGuid != null && !Objects.equals(relatedEndGuid, that.getRelatedEndGuid())) {
            return false;
        }
        if (relationshipGuid != null && !Objects.equals(relationshipGuid, that.getRelationshipGuid())) {
            return false;
        }
        LicensedReference typedThat =(LicensedReference)that;
        if (this.licenseGUID != null && !Objects.equals(this.licenseGUID,typedThat.getLicenseGUID())) {
            return false;
        }
        if (this.start != null && !Objects.equals(this.start,typedThat.getStart())) {
            return false;
        }
        if (this.end != null && !Objects.equals(this.end,typedThat.getEnd())) {
            return false;
        }
        if (this.conditions != null && !Objects.equals(this.conditions,typedThat.getConditions())) {
            return false;
        }
        if (this.licensedBy != null && !Objects.equals(this.licensedBy,typedThat.getLicensedBy())) {
            return false;
        }
        if (this.custodian != null && !Objects.equals(this.custodian,typedThat.getCustodian())) {
            return false;
        }
        if (this.licensee != null && !Objects.equals(this.licensee,typedThat.getLicensee())) {
            return false;
        }
        if (this.notes != null && !Objects.equals(this.notes,typedThat.getNotes())) {
            return false;
        }

        return Objects.equals(relatedEndType, that.getRelatedEndType()) &&
                Objects.equals(uniqueAttributes, that.getUniqueAttributes());
    }


    @Override
    public int hashCode() {
        return relatedEndGuid != null ? Objects.hash(relatedEndGuid) : Objects.hash(relatedEndType, uniqueAttributes
, this.licenseGUID
, this.start
, this.end
, this.conditions
, this.licensedBy
, this.custodian
, this.licensee
, this.notes
);
    }
}
