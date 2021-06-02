/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.communityprofile.events;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Verify the AccessServiceOutboundEventTypeTest enum contains unique message ids, non-null names and descriptions and can be
 * serialized to JSON and back again.
 */
public class AccessServiceOutboundEventTypeTest
{
    private List<Integer> existingOrdinals = new ArrayList<>();

    /**
     * Validate that a supplied ordinal is unique.
     *
     * @param ordinal value to test
     * @return boolean result
     */
    private boolean isUniqueOrdinal(int  ordinal)
    {
        if (existingOrdinals.contains(ordinal))
        {
            return false;
        }
        else
        {
            existingOrdinals.add(ordinal);
            return true;
        }
    }

    private void testSingleErrorCodeValues(CommunityProfileOutboundEventType testValue)
    {
        String                  testInfo;

        assertTrue(isUniqueOrdinal(testValue.getEventTypeCode()));
        testInfo = testValue.getEventTypeName();
        assertTrue(testInfo != null);
        assertFalse(testInfo.isEmpty());
        testInfo = testValue.getEventTypeDescription();
        assertTrue(testInfo != null);
        assertFalse(testInfo.isEmpty());
    }


    /**
     * Validated the values of the enum.
     */
    @Test public void testAllValues()
    {
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.UNKNOWN_COMMUNITY_PROFILE_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.NEW_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.UPDATED_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.DELETED_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.CLASSIFIED_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.RECLASSIFIED_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.DECLASSIFIED_ELEMENT_EVENT);
        testSingleErrorCodeValues(CommunityProfileOutboundEventType.KARMA_POINT_PLATEAU_EVENT);
    }



    /**
     * Validate that an object generated from a JSON String has the same content as the object used to
     * create the JSON String.
     */
    @Test public void testJSON()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        String       jsonString   = null;

        try
        {
            jsonString = objectMapper.writeValueAsString(CommunityProfileOutboundEventType.KARMA_POINT_PLATEAU_EVENT);
        }
        catch (Exception  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }

        try
        {
            assertTrue(objectMapper.readValue(jsonString, CommunityProfileOutboundEventType.class) == CommunityProfileOutboundEventType.KARMA_POINT_PLATEAU_EVENT);
        }
        catch (Exception  exc)
        {
            assertTrue(false, "Exception: " + exc.getMessage());
        }
    }


    /**
     * Test that toString is overridden.
     */
    @Test public void testToString()
    {
        assertTrue(
                CommunityProfileOutboundEventType.DELETED_ELEMENT_EVENT.toString().contains("CommunityProfileOutboundEventType"));
    }


    /**
     * Test that equals is working.
     */
    @Test public void testEquals()
    {
        assertTrue(
                CommunityProfileOutboundEventType.CLASSIFIED_ELEMENT_EVENT.equals(CommunityProfileOutboundEventType.CLASSIFIED_ELEMENT_EVENT));
        assertFalse(
                CommunityProfileOutboundEventType.CLASSIFIED_ELEMENT_EVENT.equals(CommunityProfileOutboundEventType.DECLASSIFIED_ELEMENT_EVENT));
    }


    /**
     * Test that hashcode is working.
     */
    @Test public void testHashcode()
    {
        assertTrue(
                CommunityProfileOutboundEventType.UPDATED_ELEMENT_EVENT.hashCode() == CommunityProfileOutboundEventType.UPDATED_ELEMENT_EVENT.hashCode());
        assertFalse(
                CommunityProfileOutboundEventType.UPDATED_ELEMENT_EVENT.hashCode() == CommunityProfileOutboundEventType.UNKNOWN_COMMUNITY_PROFILE_EVENT.hashCode());
    }
}
