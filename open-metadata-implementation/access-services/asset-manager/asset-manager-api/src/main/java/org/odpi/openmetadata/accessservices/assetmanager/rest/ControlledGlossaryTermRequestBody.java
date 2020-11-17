/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.assetmanager.rest;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.odpi.openmetadata.accessservices.assetmanager.properties.GlossaryTermProperties;
import org.odpi.openmetadata.accessservices.assetmanager.properties.GlossaryTermStatus;
import org.odpi.openmetadata.accessservices.assetmanager.properties.MetadataCorrelationProperties;

import java.io.Serializable;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


/**
 * ControlledGlossaryTermRequestBody describes the request body used to create/update controlled glossary term properties.
 */
@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ControlledGlossaryTermRequestBody implements Serializable
{
    private static final long    serialVersionUID = 1L;

    private MetadataCorrelationProperties metadataCorrelationProperties = null;
    private GlossaryTermProperties        elementProperties = null;
    private GlossaryTermStatus            initialStatus = null;

    /**
     * Default constructor
     */
    public ControlledGlossaryTermRequestBody()
    {
        super();
    }


    /**
     * Copy/clone constructor.
     *
     * @param template object to copy
     */
    public ControlledGlossaryTermRequestBody(ControlledGlossaryTermRequestBody template)
    {
        if (template != null)
        {
            metadataCorrelationProperties = template.getMetadataCorrelationProperties();
            elementProperties = template.getElementProperties();
            initialStatus = template.getInitialStatus();
        }
    }


    /**
     * Return the properties used to correlate the external metadata element with the open metadata element.
     *
     * @return properties object
     */
    public MetadataCorrelationProperties getMetadataCorrelationProperties()
    {
        return metadataCorrelationProperties;
    }


    /**
     * Set up the properties used to correlate the external metadata element with the open metadata element.
     *
     * @param metadataCorrelationProperties properties object
     */
    public void setMetadataCorrelationProperties(MetadataCorrelationProperties metadataCorrelationProperties)
    {
        this.metadataCorrelationProperties = metadataCorrelationProperties;
    }


    /**
     * Return the properties for the element.
     *
     * @return properties object
     */
    public GlossaryTermProperties getElementProperties()
    {
        return elementProperties;
    }


    /**
     * Set up the properties for the element.
     *
     * @param elementProperties properties object
     */
    public void setElementProperties(GlossaryTermProperties elementProperties)
    {
        this.elementProperties = elementProperties;
    }


    /**
     * Return the initial status for the controlled glossary term.  By default it is "DRAFT".
     *
     * @return glossary term status enum
     */
    public GlossaryTermStatus getInitialStatus()
    {
        return initialStatus;
    }


    /**
     * Set up the initial status for the controlled glossary term.  By default it is "DRAFT".
     *
     * @param initialStatus glossary term status enum
     */
    public void setInitialStatus(GlossaryTermStatus initialStatus)
    {
        this.initialStatus = initialStatus;
    }


    /**
     * JSON-style toString
     *
     * @return return string containing the property names and values
     */
    @Override
    public String toString()
    {
        return "ControlledGlossaryTermRequestBody{" +
                       "metadataCorrelationProperties=" + metadataCorrelationProperties +
                       ", elementProperties=" + elementProperties +
                       ", initialStatus=" + initialStatus +
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
        ControlledGlossaryTermRequestBody that = (ControlledGlossaryTermRequestBody) objectToCompare;
        return Objects.equals(getMetadataCorrelationProperties(), that.getMetadataCorrelationProperties()) &&
                       Objects.equals(getElementProperties(), that.getElementProperties());
    }



    /**
     * Return hash code for this object
     *
     * @return int hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), metadataCorrelationProperties, elementProperties, initialStatus);
    }
}
