/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.digitalarchitecture.fvt.execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.odpi.openmetadata.accessservices.digitalarchitecture.fvt.validvalues.CreateValidValuesSetTest;
import org.odpi.openmetadata.fvt.utilities.FVTConstants;
import org.odpi.openmetadata.fvt.utilities.FVTResults;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * DigitalArchitectureOMASCreateValidValuesSetIT is the failsafe wrapper for CreateValidValuesSetTest.
 */
public class DigitalArchitectureOMASCreateValidValuesSetIT
{
    @ParameterizedTest
    @ValueSource(strings = {FVTConstants.IN_MEMORY_SERVER, FVTConstants.GRAPH_SERVER})
    public void testCreateValidValuesSet(String serverName)
    {
        FVTResults results = CreateValidValuesSetTest.performFVT(serverName, FVTConstants.SERVER_PLATFORM_URL_ROOT, FVTConstants.USERID);

        results.printResults();
        assertTrue(results.isSuccessful());
    }
}
