/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.userinterface.uichassis.springboot.auth;


import org.odpi.openmetadata.userinterface.uichassis.springboot.domain.User;
import org.springframework.security.core.Authentication;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenAuthService extends  TokenSettings implements AuthService{

    public User addAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        TokenUser tokenUser = getTokenUser(authentication);
        String token = createTokenForUser(tokenUser.getUser(), tokenSecret);
        response.addHeader(AUTH_HEADER_NAME, token);

        return tokenUser.getUser();
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null && !token.isEmpty()) {
            final TokenUser user = parseUserFromToken(token, tokenSecret);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

}
