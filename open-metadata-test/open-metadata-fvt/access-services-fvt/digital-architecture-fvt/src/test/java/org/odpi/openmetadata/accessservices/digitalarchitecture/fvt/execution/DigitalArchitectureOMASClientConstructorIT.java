/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.digitalarchitecture.fvt.execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.odpi.openmetadata.accessservices.digitalarchitecture.fvt.clientconstructors.ClientConstructorTest;
import org.odpi.openmetadata.fvt.utilities.FVTConstants;
import org.odpi.openmetadata.fvt.utilities.FVTResults;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * DigitalArchitectureOMASClientConstructorIT is the failsafe wrapper for ClientConstructorTest.
 */
public class DigitalArchitectureOMASClientConstructorIT
{
    @ParameterizedTest
    @ValueSource(strings = {FVTConstants.IN_MEMORY_SERVER, FVTConstants.GRAPH_SERVER})
    public void testClientConstructors(String serverName)
    {
        FVTResults results = ClientConstructorTest.performFVT(serverName, FVTConstants.SERVER_PLATFORM_URL_ROOT);

        results.printResults();
        assertTrue(results.isSuccessful());
    }
}
