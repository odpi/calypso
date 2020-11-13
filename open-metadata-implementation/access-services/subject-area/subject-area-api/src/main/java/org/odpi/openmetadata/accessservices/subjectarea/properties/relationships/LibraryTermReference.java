/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
// This is a generated file - do not edit - changes should be made to the templates amd/or generator to generate this file with changes.

package org.odpi.openmetadata.accessservices.subjectarea.properties.relationships;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.Line;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.LineEnd;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.RelationshipEndCardinality;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * LibraryTermReference is a relationship between a Term and an ExternalGlossaryLink.
 */
@JsonAutoDetect(getterVisibility = PUBLIC_ONLY, setterVisibility = PUBLIC_ONLY, fieldVisibility = NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryTermReference extends Line {
    private String description = "Links a glossary term to a glossary term in an external glossary.";

    /*
     * Set up end 1.
     */
     protected static final String END_1_NODE_TYPE = "Term";
     protected static final String END_1_ATTRIBUTE_NAME = "localTerms";
     protected static final String END_1_ATTRIBUTE_DESCRIPTION = "Related local glossary categories.";
     protected static final RelationshipEndCardinality END_1_CARDINALITY = RelationshipEndCardinality.ANY_NUMBER;
     protected static final LineEnd LINE_END_1 = new LineEnd(END_1_NODE_TYPE,
            END_1_ATTRIBUTE_NAME, END_1_ATTRIBUTE_DESCRIPTION, END_1_CARDINALITY);

    /*
     * Set up end 2.
     */
     protected static final String END_2_NODE_TYPE = "ExternalGlossaryLink";
     protected static final String END_2_ATTRIBUTE_NAME = "externalGlossaryTerms";
     protected static final String END_2_ATTRIBUTE_DESCRIPTION = "Links to related external glossaries.";
     protected static final RelationshipEndCardinality END_2_CARDINALITY = RelationshipEndCardinality.ANY_NUMBER;
     protected static final LineEnd LINE_END_2 = new LineEnd(END_2_NODE_TYPE,
            END_2_ATTRIBUTE_NAME, END_2_ATTRIBUTE_DESCRIPTION, END_2_CARDINALITY);

    private String identifier;
    private String steward;
    private Date lastVerified;

    public LibraryTermReference() {
        super("LibraryTermReference", "38c346e4-ddd2-42ef-b4aa-55d53c078d22", LINE_END_1, LINE_END_2);
    }

    /**
     * {@literal Identifier of the corresponding element from the external glossary. }
     *
     * @return {@code String }
     */
    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * {@literal Description of the corresponding element from the external glossary. }
     *
     * @return {@code String }
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * {@literal Set the description of the relationship. }
     *
     * @param description {@code String }
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@literal Person who established the link to the external glossary. }
     *
     * @return {@code String }
     */
    public String getSteward() {
        return this.steward;
    }

    public void setSteward(String steward) {
        this.steward = steward;
    }

    /**
     * {@literal Date when this reference was last checked. }
     *
     * @return {@code Date }
     */
    public Date getLastVerified() {
        return this.lastVerified;
    }

    public void setLastVerified(Date lastVerified) {
        this.lastVerified = lastVerified;
    }

    @Override
    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append(" LibraryTermReference=");
        sb.append(super.toString(sb));
        sb.append(" LibraryTermReference Attributes{");
        sb.append("identifier=").append(this.identifier).append(",");
        sb.append("description=").append(this.description).append(",");
        sb.append("steward=").append(this.steward).append(",");
        sb.append("lastVerified=").append(this.lastVerified).append(",");
        sb.append("}");
        return sb;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
}