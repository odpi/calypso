/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.connectors.properties.beans;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * This property header implements any common mechanisms that all property objects need.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "class")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = RelatedAsset.class, name = "RelatedAsset"),
                @JsonSubTypes.Type(value = ElementHeader.class, name = "ElementHeader"),
                @JsonSubTypes.Type(value = ElementControlHeader.class, name = "ElementControlHeader"),
                @JsonSubTypes.Type(value = SchemaAttributeRelationship.class, name = "SchemaAttributeRelationship"),
                @JsonSubTypes.Type(value = EmbeddedConnection.class, name = "EmbeddedConnection")
        })
public abstract class PropertyBase implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final long CURRENT_AUDIT_HEADER_VERSION = 1;

    /*
     * Version number for this header.  This is used to ensure that all of the critical header information
     * in read in a back-level version of the OCF.  The default is 0 to indicate that the instance came from
     * a version of the OCF that does not have a version number encoded.
     */
    private long headerVersion = 0;

    /**
     * Typical Constructor
     */
    public PropertyBase()
    {
    }


    /**
     * Copy/clone Constructor the resulting object will return true if tested with this.equals(template) as
     * long as the template object is not null;
     *
     * @param template object being copied
     */
    public PropertyBase(PropertyBase template)
    {
        if (template != null)
        {
            headerVersion = template.getHeaderVersion();
        }
    }


    /**
     * Return the version of this header.  This is used by the OMRS to determine if it is back level and
     * should not process events from a source that is more advanced because it does not have the ability
     * to receive all of the header properties.
     *
     * @return long version number - the value is incremented each time a new non-informational field is added
     * to the audit header.
     */
    public long getHeaderVersion()
    {
        return headerVersion;
    }


    /**
     * Return the version of this header.  This is used by the OMRS to determine if it is back level and
     * should not process events from a source that is more advanced because it does not have the ability
     * to receive all of the header properties.
     *
     * @param headerVersion long version number - the value is incremented each time a new non-informational field is added
     * to the audit header.
     */
    public void setHeaderVersion(long headerVersion)
    {
        this.headerVersion = headerVersion;
    }
}