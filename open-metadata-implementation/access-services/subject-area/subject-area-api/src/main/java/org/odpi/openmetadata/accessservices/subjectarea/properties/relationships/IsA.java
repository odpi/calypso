/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
// This is a generated file - do not edit - changes should be made to the templates amd/or generator to generate this file with changes.

package org.odpi.openmetadata.accessservices.subjectarea.properties.relationships;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.subjectarea.properties.enums.TermRelationshipStatus;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.Line;
import org.odpi.openmetadata.accessservices.subjectarea.properties.objects.graph.LineEnd;
import org.odpi.openmetadata.repositoryservices.connectors.stores.metadatacollectionstore.properties.typedefs.RelationshipEndCardinality;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * Link between a more general glossary term and a more specific definition.
 */
@JsonAutoDetect(getterVisibility = PUBLIC_ONLY, setterVisibility = PUBLIC_ONLY, fieldVisibility = NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IsA extends Line {
    private String description = "Link between a more general glossary term and a more specific definition.";

    /*
     * Set up end 1.
     */
     protected static final String END_1_NODE_TYPE = "Term";
     protected static final String END_1_ATTRIBUTE_NAME = "classifies";
     protected static final String END_1_ATTRIBUTE_DESCRIPTION = "More specific glossary terms.";
     protected static final RelationshipEndCardinality END_1_CARDINALITY = RelationshipEndCardinality.ANY_NUMBER;
     protected static final LineEnd LINE_END_1 = new LineEnd(END_1_NODE_TYPE,
            END_1_ATTRIBUTE_NAME, END_1_ATTRIBUTE_DESCRIPTION, END_1_CARDINALITY);

    /*
     * Set up end 2.
     */
     protected static final String END_2_NODE_TYPE = "Term";
     protected static final String END_2_ATTRIBUTE_NAME = "isA";
     protected static final String END_2_ATTRIBUTE_DESCRIPTION = "More general glossary terms.";
     protected static final RelationshipEndCardinality END_2_CARDINALITY = RelationshipEndCardinality.ANY_NUMBER;
     protected static final LineEnd LINE_END_2 = new LineEnd(END_2_NODE_TYPE,
            END_2_ATTRIBUTE_NAME, END_2_ATTRIBUTE_DESCRIPTION, END_2_CARDINALITY);

    private String expression;
    private TermRelationshipStatus status;
    private String steward;
    private String source;

    public IsA() {
        super("Isa", "50fab7c7-68bc-452f-b8eb-ec76829cac85", LINE_END_1, LINE_END_2);
    }

    /**
     * {@literal Description of the relationship. }
     *
     * @return {@code String }
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * {@literal Set the description of the relationship. }
     * @param description {@code String }
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@literal An expression that explains the relationship. }
     *
     * @return {@code String }
     */
    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * {@literal The status of or confidence in the relationship. }
     *
     * @return {@code TermRelationshipStatus }
     */
    public TermRelationshipStatus getStatus() {
        return this.status;
    }

    public void setStatus(TermRelationshipStatus status) {
        this.status = status;
    }

    /**
     * {@literal Person responsible for the relationship. }
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
     * {@literal Person, organization or automated process that created the relationship. }
     *
     * @return {@code String }
     */
    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public StringBuilder toString(StringBuilder sb) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append(" IsA=");
        sb.append(super.toString(sb));
        sb.append(" IsA Attributes{");
        sb.append("description=").append(this.description).append(",");
        sb.append("expression=").append(this.expression).append(",");
        sb.append("steward=").append(this.steward).append(",");
        sb.append("source=").append(this.source).append(",");
        if (status != null) {
            sb.append("status=").append(status.name());
        }
        sb.append("}");
        return sb;
    }

    @Override
    public String toString() {
        return toString(new StringBuilder()).toString();
    }
}