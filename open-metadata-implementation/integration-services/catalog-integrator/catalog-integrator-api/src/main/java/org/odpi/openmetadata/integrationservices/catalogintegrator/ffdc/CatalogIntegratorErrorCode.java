/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.integrationservices.catalogintegrator.ffdc;

import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageDefinition;
import org.odpi.openmetadata.frameworks.auditlog.messagesets.ExceptionMessageSet;

/**
 * The CatalogIntegratorErrorCode error code is used to define first failure data capture (FFDC) for errors that
 * occur when working with the Integration Services.  It is used in conjunction with all exceptions,
 * both Checked and Runtime (unchecked).
 *
 * The 5 fields in the enum are:
 * <ul>
 *     <li>HTTP Error Code for translating between REST and JAVA - Typically the numbers used are:</li>
 *     <li><ul>
 *         <li>500 - internal error</li>
 *         <li>400 - invalid parameters</li>
 *         <li>404 - not found</li>
 *         <li>409 - data conflict errors - eg item already defined</li>
 *     </ul></li>
 *     <li>Error Message Id - to uniquely identify the message</li>
 *     <li>Error Message Text - includes placeholder to allow additional values to be captured</li>
 *     <li>SystemAction - describes the result of the error</li>
 *     <li>UserAction - describes how a user should correct the error</li>
 * </ul>
 */
public enum CatalogIntegratorErrorCode implements ExceptionMessageSet
{

    INVALID_CONNECTOR(400,"CATALOG-INTEGRATOR-400-001",
                  "Integration connector {0} is not of the correct type to run in the {1} integration service.  It must inherit from {2}",
                  "The integration service fails to start and this in turn causes the integration daemon to fail.",
                  "The connector was configured through the administration calls for the integration service." +
                              "Either move it to an appropriate integration service or update the connector implementation " +
                              "to inherit from the correct class."),

    DISABLED_EXCHANGE_SERVICE(400,"CATALOG-INTEGRATOR-400-002",
                      "The {0} has been disabled by the configuration for the {1} integration service",
                      "The integration service's context is unable to return the client interface to this service.",
                      "The exchange service was disabled through the administration calls for the integration service." +
                              "Either change the configuration of the integration service or change the connector to skip the part of the " +
                              "synchronization that uses this exchange service since the organization does not want this type of metadata synchronized."),

    NOT_PERMITTED_SYNCHRONIZATION(400,"CATALOG-INTEGRATOR-400-003",
                              "The permitted synchronization direction of {0} does not allow connector {1} to issue {2} requests on behalf of asset manager {3}",
                              "The request is not issued and an exception is returned to the caller.",
                              "The request was disabled through the administration calls for the integration connector." +
                                      "Either change the configuration of the integration service or change the connector to skip the part of the " +
                                      "synchronization that uses this request since the organization does not want this type of metadata synchronized."),

    BAD_CONFIG_PROPERTIES(400, "CATALOG-INTEGRATOR-400-004",
                          "The {0} Open Metadata Integration Service (OMIS) has been passed an invalid value of {1} in the {2} property.  The resulting exception of {3} included the following message: {4}",
                          "The access service has not been passed valid configuration .",
                          "Correct the value of the failing configuration property and restart the server."),
    ;


    private ExceptionMessageDefinition messageDefinition;


    /**
     * The constructor for CatalogIntegratorErrorCode expects to be passed one of the enumeration rows defined in
     * CatalogIntegratorErrorCode above.   For example:
     *
     *     CatalogIntegratorErrorCode   errorCode = CatalogIntegratorErrorCode.UNKNOWN_ENDPOINT;
     *
     * This will expand out to the 5 parameters shown below.
     *
     * @param httpErrorCode   error code to use over REST calls
     * @param errorMessageId   unique Id for the message
     * @param errorMessage   text for the message
     * @param systemAction   description of the action taken by the system when the error condition happened
     * @param userAction   instructions for resolving the error
     */
    CatalogIntegratorErrorCode(int  httpErrorCode, String errorMessageId, String errorMessage, String systemAction, String userAction)
    {
        this.messageDefinition = new ExceptionMessageDefinition(httpErrorCode,
                                                                errorMessageId,
                                                                errorMessage,
                                                                systemAction,
                                                                userAction);
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are no message inserts.
     *
     * @return message definition object.
     */
    public ExceptionMessageDefinition getMessageDefinition()
    {
        return messageDefinition;
    }


    /**
     * Retrieve a message definition object for an exception.  This method is used when there are values to be inserted into the message.
     *
     * @param params array of parameters (all strings).  They are inserted into the message according to the numbering in the message text.
     * @return message definition object.
     */
    public ExceptionMessageDefinition getMessageDefinition(String... params)
    {
        messageDefinition.setMessageParameters(params);

        return messageDefinition;
    }
}
