/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.governanceprogram.fvt.errorhandling;

import org.odpi.openmetadata.accessservices.governanceprogram.client.GovernanceDefinitionManager;
import org.odpi.openmetadata.accessservices.governanceprogram.client.GovernanceDefinitionManager;
import org.odpi.openmetadata.accessservices.governanceprogram.properties.GovernanceDefinitionProperties;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.fvt.utilities.exceptions.FVTUnexpectedCondition;

/**
 * CreateGovernanceDefinitionInvalidParameterTest tests the possibilities for invalid parameters on the createGovernanceDefinition method
 */
class CreateGovernanceDefinitionInvalidParameterTest
{
    private final String testCaseName   = "CreateGovernanceDefinitionInvalidParameterTest";

    /**
     * Test combinations of invalid parameters passed to createGovernanceDefinition.
     *
     * @param userId calling user
     * @param client client to call
     * @throws FVTUnexpectedCondition the test case failed
     */
    CreateGovernanceDefinitionInvalidParameterTest(String                      userId,
                                                   GovernanceDefinitionManager client) throws FVTUnexpectedCondition
    {
        final String activityName = "testCreateGovernanceDefinition";

        try
        {
            testCreateGovernanceDefinitionNoUserId(client);
            testCreateGovernanceDefinitionNoProperties(client, userId);
            testCreateGovernanceDefinitionNoQualifiedName(client, userId);
        }
        catch (Throwable unexpectedError)
        {
            throw new FVTUnexpectedCondition(testCaseName, activityName, unexpectedError);
        }
    }


    /**
     * Test null userId passed to createGovernanceDefinition.
     *
     * @param client client to call
     * @throws FVTUnexpectedCondition the test case failed
     */
    private void testCreateGovernanceDefinitionNoUserId(GovernanceDefinitionManager client) throws FVTUnexpectedCondition
    {
        final String activityName = "testCreateGovernanceDefinitionNoUserId";

        GovernanceDefinitionProperties properties = new GovernanceDefinitionProperties();

        try
        {
            client.createGovernanceDefinition(null, properties);
            throw new FVTUnexpectedCondition(testCaseName, activityName);
        }
        catch (InvalidParameterException expectedException)
        {
            // ignore
        }
        catch (Throwable unexpectedError)
        {
            throw new FVTUnexpectedCondition(testCaseName, activityName, unexpectedError);
        }
    }


    /**
     * Test null properties passed to createGovernanceDefinition.
     *
     * @param client client to call
     * @param userId calling user
     * @throws FVTUnexpectedCondition the test case failed
     */
    private void testCreateGovernanceDefinitionNoProperties(GovernanceDefinitionManager client,
                                                         String                   userId) throws FVTUnexpectedCondition
    {
        final String activityName = "testCreateGovernanceDefinitionNoProperties";

        try
        {
            client.createGovernanceDefinition(userId,null);
            throw new FVTUnexpectedCondition(testCaseName, activityName);
        }
        catch (InvalidParameterException expectedException)
        {
            // ignore
        }
        catch (Throwable unexpectedError)
        {
            throw new FVTUnexpectedCondition(testCaseName, activityName, unexpectedError);
        }
    }


    /**
     * Test null properties passed to createGovernanceDefinition.
     *
     * @param client client to call
     * @param userId calling user
     * @throws FVTUnexpectedCondition the test case failed
     */
    private void testCreateGovernanceDefinitionNoQualifiedName(GovernanceDefinitionManager client,
                                                            String                      userId) throws FVTUnexpectedCondition
    {
        final String activityName = "testCreateGovernanceDefinitionNoQualifiedName";

        try
        {
            GovernanceDefinitionProperties properties = new GovernanceDefinitionProperties();

            client.createGovernanceDefinition(userId, properties);
            throw new FVTUnexpectedCondition(testCaseName, activityName);
        }
        catch (InvalidParameterException expectedException)
        {
            // ignore
        }
        catch (Throwable unexpectedError)
        {
            throw new FVTUnexpectedCondition(testCaseName, activityName, unexpectedError);
        }
    }
}
