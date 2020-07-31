package de.cidaas.interceptor.authentication;

import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import de.cidaas.jwt.JWT;
import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.interfaces.DecodedJWT;

@SuppressWarnings("serial")
public class JwtPreAuthentication implements Authentication {

    private static Logger logger = LoggerFactory.getLogger(JwtPreAuthentication.class);

    private final DecodedJWT token;

    JwtPreAuthentication(DecodedJWT token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return token.getToken();
    }

    @Override
    public Object getDetails() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    	 logger.debug("Could not authenticate method {setAuthenticated}", JwtPreAuthentication.class.getName());

    }

    @Override
    public String getName() {
        return token.getSubject();
    }

    public static JwtPreAuthentication usingToken(String token) {
        if (token == null) {
            logger.debug("No token was provided to build {}", JwtPreAuthentication.class.getName());
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new JwtPreAuthentication(jwt);
        } catch (JWTDecodeException e) {
            logger.debug("Failed to decode token as jwt", e);
            return null;
        }
    }

    public String getToken() {
        return token.getToken();
    }

    public String getKeyId() {
        return token.getKeyId();
    }
}
