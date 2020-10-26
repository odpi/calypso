/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.datamanager.fvt;

import org.odpi.openmetadata.accessservices.datamanager.fvt.clientconstructors.ClientConstructorTest;
import org.odpi.openmetadata.accessservices.datamanager.fvt.databases.CreateDatabaseTest;
import org.odpi.openmetadata.accessservices.datamanager.fvt.errorhandling.InvalidParameterTest;
import org.odpi.openmetadata.fvt.utilities.FVTConstants;
import org.odpi.openmetadata.fvt.utilities.FVTResults;
import org.odpi.openmetadata.fvt.utilities.FVTSuiteBase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import static java.lang.System.exit;

/**
 * DataManagerOMASFVTSuite provides the main program for the Data Manager OMAS
 * Functional Verification Tests (FVTs).  It is used when running the test suite standalone
 * (ie outside of the failsafe test framework).
 */
public class DataManagerOMASFVTSuite extends FVTSuiteBase
{
    /**
     * Run all of the defined tests and capture the results.
     *
     * @param serverName name of the server to connect to
     * @param serverPlatformRootURL the network address of the server running the OMAS REST servers
     * @param userId calling user
     * @return combined results of running test
     */
    protected int performFVT(String   serverName,
                             String   serverPlatformRootURL,
                             String   userId)
    {
        int returnCode = 0;

        FVTResults results;

        results = ClientConstructorTest.performFVT(serverName, serverPlatformRootURL);
        if (! results.isSuccessful())
        {
            returnCode --;
        }
        results.printResults();

        results = InvalidParameterTest.performFVT(serverName, serverPlatformRootURL, userId);
        if (! results.isSuccessful())
        {
            returnCode --;
        }
        results.printResults();

        results = CreateDatabaseTest.performFVT(serverName, serverPlatformRootURL, userId);
        if (! results.isSuccessful())
        {
            returnCode --;
        }
        results.printResults();

        return returnCode;
    }
}
