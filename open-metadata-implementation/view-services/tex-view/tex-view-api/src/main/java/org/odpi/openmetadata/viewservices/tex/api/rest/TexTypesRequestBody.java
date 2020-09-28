/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.viewservices.tex.api.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY;


@JsonAutoDetect(getterVisibility=PUBLIC_ONLY, setterVisibility=PUBLIC_ONLY, fieldVisibility=NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TexTypesRequestBody {


    /*
     * The TexTypesRequestBody class provides a body for REST requests to perform a load of type information
     */

    private String                    serverName;                    // must be non-null
    private String                    serverRootURL;                 // must be non-null
    private Boolean                   enterpriseOption;


    public TexTypesRequestBody() {
       // No initialization yet
    }

    /*
     * Getters for Jackson
     */

    public String getServerName() { return serverName; }

    public String getServerRootURL() { return serverRootURL; }

    public Boolean getEnterpriseOption() { return enterpriseOption; }


    public void setServerName(String serverName) { this.serverName = serverName; }

    public void setServerRootURL(String serverRootURL) { this.serverRootURL = serverRootURL; }

    public void setEnterpriseOption(Boolean enterpriseOption) { this.enterpriseOption = enterpriseOption; }



    @Override
    public String toString()
    {
        return "TexTypesRequestBody{" +
                ", serverName=" + serverName +
                ", serverRootURL=" + serverRootURL +
                ", enterpriseOption=" + enterpriseOption +
                '}';
    }



}
