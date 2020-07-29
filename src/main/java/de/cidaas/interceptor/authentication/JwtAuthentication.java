package de.cidaas.interceptor.authentication;

import org.springframework.security.core.Authentication;

import de.cidaas.jwt.JWTVerifier;
import de.cidaas.jwt.exceptions.JWTVerificationException;

public interface JwtAuthentication {

    String getToken();

    String getKeyId();

    Authentication verify(JWTVerifier verifier) throws JWTVerificationException;
}
