/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.datamanager.metadataelements;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.datamanager.properties.DatabaseManagerProperties;
import org.odpi.openmetadata.accessservices.datamanager.properties.SoftwareServerCapabilitiesProperties;

import java.io.Serializable;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;

/**
 * SoftwareServerCapabilityElement contains the properties and header for a software server capabilities entity retrieved from the metadata
 * repository.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SoftwareServerCapabilityElement implements MetadataElement, Serializable
{
    private static final long     serialVersionUID = 1L;

    private ElementHeader                        elementHeader = null;
    private SoftwareServerCapabilitiesProperties softwareServerCapabilitiesProperties = null;


    /**
     * Default constructor
     */
    public SoftwareServerCapabilityElement()
    {
        super();
    }


    /**
     * Copy/clone constructor
     *
     * @param template object to copy
     */
    public SoftwareServerCapabilityElement(SoftwareServerCapabilityElement template)
    {
        if (template != null)
        {
            elementHeader = template.getElementHeader();
            softwareServerCapabilitiesProperties = template.getSoftwareServerCapabilitiesProperties();
        }
    }


    /**
     * Return the element header associated with the properties.
     *
     * @return element header object
     */
    @Override
    public ElementHeader getElementHeader()
    {
        return elementHeader;
    }


    /**
     * Set up the element header associated with the properties.
     *
     * @param elementHeader element header object
     */
    @Override
    public void setElementHeader(ElementHeader elementHeader)
    {
        this.elementHeader = elementHeader;
    }


    /**
     * Return the properties of the software server capability.
     *
     * @return properties bean
     */
    public SoftwareServerCapabilitiesProperties getSoftwareServerCapabilitiesProperties()
    {
        return softwareServerCapabilitiesProperties;
    }


    /**
     * Set up the properties of the software server capability.
     *
     * @param softwareServerCapabilitiesProperties properties bean
     */
    public void setSoftwareServerCapabilitiesProperties(SoftwareServerCapabilitiesProperties softwareServerCapabilitiesProperties)
    {
        this.softwareServerCapabilitiesProperties = softwareServerCapabilitiesProperties;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "SoftwareServerCapabilityElement{" +
                "elementHeader=" + elementHeader +
                ", softwareServerCapabilitiesProperties=" + softwareServerCapabilitiesProperties +
                '}';
    }


    /**
     * Return comparison result based on the content of the properties.
     *
     * @param objectToCompare test object
     * @return result of comparison
     */
    @Override
    public boolean equals(Object objectToCompare)
    {
        if (this == objectToCompare)
        {
            return true;
        }
        if (objectToCompare == null || getClass() != objectToCompare.getClass())
        {
            return false;
        }
        if (!super.equals(objectToCompare))
        {
            return false;
        }
        SoftwareServerCapabilityElement that = (SoftwareServerCapabilityElement) objectToCompare;
        return Objects.equals(elementHeader, that.elementHeader) &&
                Objects.equals(softwareServerCapabilitiesProperties, that.softwareServerCapabilitiesProperties);
    }


    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), elementHeader, softwareServerCapabilitiesProperties);
    }
}
