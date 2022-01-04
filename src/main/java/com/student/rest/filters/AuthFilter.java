package com.student.rest.filters;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;

//@Provider
//@PreMatching
public class AuthFilter implements ContainerRequestFilter {
    private static final String AUTHORIZATION_HEADER="Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX="Basic ";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) throw new NotAuthorizedException("Bearer");
//        String token="Basic Og==";
        String token = parseToken(authHeader);
        String decodedString= String.valueOf(Base64.getDecoder().decode(token));
        if (verifyToken(token) == false) {
            throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
        }
    }

    private boolean verifyToken(String token) {
        if (token.equals("Basic Og=="))
            return true;
        return false;
    }

    private String parseToken(String authHeader) {
        return authHeader.replaceFirst(AUTHORIZATION_HEADER_PREFIX,"");
    }
}
