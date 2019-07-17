/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.dataplatform.server;

import org.odpi.openmetadata.accessservices.dataplatform.admin.DataPlatformAdmin;
import org.odpi.openmetadata.adminservices.OMAGAccessServiceRegistration;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceDescription;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceOperationalStatus;
import org.odpi.openmetadata.adminservices.configuration.registration.AccessServiceRegistration;

class DataPlatformOMASRegistration {


    DataPlatformOMASRegistration() {

            AccessServiceDescription myDescription = AccessServiceDescription.DATA_PLATFORM_OMAS;
            AccessServiceRegistration myRegistration = new AccessServiceRegistration(myDescription,
                    AccessServiceOperationalStatus.ENABLED,
                    DataPlatformAdmin.class.getName()

            );
            OMAGAccessServiceRegistration.registerAccessService(myRegistration);
        }
}


