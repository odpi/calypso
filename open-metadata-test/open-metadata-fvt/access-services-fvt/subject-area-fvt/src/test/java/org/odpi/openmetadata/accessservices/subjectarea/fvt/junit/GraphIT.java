/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */

package org.odpi.openmetadata.accessservices.subjectarea.fvt.junit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.odpi.openmetadata.accessservices.subjectarea.fvt.GraphFVT;
import org.odpi.openmetadata.http.HttpHelper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GraphIT {
    @BeforeAll
    public static void disableStrictSSL(){
        HttpHelper.noStrictSSL();
    }

    @ParameterizedTest
    @ValueSource(strings = {"serverinmem","servergraph"})
    public void testGraph(String server) {
        assertDoesNotThrow(() -> GraphFVT.runIt("https://localhost:10443", server, "garygeeke"));
    }
}
