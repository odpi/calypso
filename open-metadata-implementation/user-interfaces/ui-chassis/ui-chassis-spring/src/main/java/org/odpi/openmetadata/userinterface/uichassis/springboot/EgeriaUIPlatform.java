/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.userinterface.uichassis.springboot;

import org.odpi.openmetadata.accessservices.assetcatalog.AssetCatalog;
import org.odpi.openmetadata.accessservices.glossaryview.client.GlossaryViewClient;
import org.odpi.openmetadata.accessservices.subjectarea.SubjectArea;
import org.odpi.openmetadata.accessservices.subjectarea.client.SubjectAreaImpl;
import org.odpi.openmetadata.frameworks.connectors.ffdc.InvalidParameterException;
import org.odpi.openmetadata.governanceservers.openlineage.client.OpenLineageClient;
import org.odpi.openmetadata.http.HttpHelper;
import org.odpi.openmetadata.userinterface.uichassis.springboot.auth.AuthService;
import org.odpi.openmetadata.userinterface.uichassis.springboot.auth.SessionAuthService;
import org.odpi.openmetadata.userinterface.uichassis.springboot.auth.TokenAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan({"org.odpi.openmetadata.*"})
@Configuration
public class EgeriaUIPlatform {

    private static final Logger LOG = LoggerFactory.getLogger(EgeriaUIPlatform.class);
    @Autowired
    private Environment env;

    @Value("${strict.ssl}")
    Boolean strictSSL;

    public static void main(String[] args) {
        SpringApplication.run(EgeriaUIPlatform.class, args);
    }

    @Bean
    public InitializingBean getInitialize()
    {
        return () -> {
            if (!strictSSL)
            {
                HttpHelper.noStrictSSL();
            }
        };
    }


    @Bean
    public AssetCatalog getAssetCatalog(@Value("${omas.server.url}") String serverUrl,
                                        @Value("${omas.server.name}") String serverName) throws InvalidParameterException {
        return new AssetCatalog(serverName, serverUrl);
    }

    @Bean
    public SubjectArea getSubjectArea(@Value("${omas.server.url}") String serverUrl,
                                      @Value("${omas.server.name}") String serverName) throws InvalidParameterException {
        return new SubjectAreaImpl(serverName, serverUrl);
    }

    @Bean
    public GlossaryViewClient getGlossaryViewClient(@Value("${omas.server.url}") String serverUrl,
                                             @Value("${omas.server.name}") String serverName) throws InvalidParameterException {
        return new GlossaryViewClient(serverName, serverUrl);
    }

    @Bean
    public OpenLineageClient getOpenLineage(@Value("${open.lineage.server.url}") String serverUrl,
                                            @Value("${open.lineage.server.name}") String serverName) throws InvalidParameterException {
        return new OpenLineageClient(serverName, serverUrl);
    }

    @Bean
    public AuthService getAuthService(@Value("${authentication.mode}") String authenticationMode)  {
        if(null == authenticationMode || authenticationMode.isEmpty() || "token".equals(authenticationMode)){
            return new TokenAuthService();
        }
        return new SessionAuthService();
    }

    @PostConstruct
    private void configureTrustStore() {

        //making sure truststore was not set using JVM options
        // and strict.ssl is true ( if false, truststore will ignored anyway )
        if(strictSSL && System.getProperty("javax.net.ssl.trustStore")==null) {
            //load the 'javax.net.ssl.trustStore' and
            //'javax.net.ssl.trustStorePassword' from application.properties
            System.setProperty("javax.net.ssl.trustStore", env.getProperty("server.ssl.trust-store"));
            System.setProperty("javax.net.ssl.trustStorePassword", env.getProperty("server.ssl.trust-store-password"));
        }
    }
}



