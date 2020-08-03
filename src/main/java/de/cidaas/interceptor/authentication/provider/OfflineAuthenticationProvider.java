package de.cidaas.interceptor.authentication.provider;

import java.security.interfaces.RSAPublicKey;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.interceptor.authentication.JwtAuthentication;
import de.cidaas.jwk.InvalidPublicKeyException;
import de.cidaas.jwk.Jwk;
import de.cidaas.jwk.JwkException;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwk.SigningKeyNotFoundException;
import de.cidaas.jwt.JWT;
import de.cidaas.jwt.JWTVerifier;
import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.exceptions.JWTVerificationException;

public class OfflineAuthenticationProvider implements AuthenticationProvider {

    private final byte[] secret;
    private final String issuer;
    private final String clientId;
    private final JwkProvider jwkProvider;

    private long leeway = 0;

    public OfflineAuthenticationProvider(String clientId, String issuer, byte[] secret) {
    	this.clientId = clientId;
    	this.issuer = issuer;
    	this.secret = secret;
        this.jwkProvider = null;
    }

    public OfflineAuthenticationProvider(String clientId, String issuer, JwkProvider jwkProvider) {
    	this.clientId = clientId;
    	this.issuer = issuer;
    	this.jwkProvider = jwkProvider;
        this.secret = null;
        
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        JwtAuthentication jwt = (JwtAuthentication) authentication;
        
        try {
        	JWTVerifier jwtVerifier = jwtVerifier(jwt);
        	jwtVerifier.verify(jwt.getCredentials().getTokenAsString());
        	
        	jwt.setAuthenticated(true);
            return jwt;
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Not a valid token", e);
        }
    }

    public OfflineAuthenticationProvider withJwtVerifierLeeway(long leeway) {
        this.leeway = leeway;
        return this;
    }

    private JWTVerifier jwtVerifier(JwtAuthentication authentication) throws AuthenticationException {
        if (secret != null) {
            return providerForHS256(secret, issuer, clientId, leeway);
        }
        
        final String kid = authentication.getCredentials().getKeyId();
        
        if (kid == null) {
            throw new BadCredentialsException("No kid found in jwt");
        }
        if (jwkProvider == null) {
            throw new AuthenticationServiceException("Missing jwk provider");
        }
        try {
            final Jwk jwk = jwkProvider.get(kid);
            return providerForRS256((RSAPublicKey) jwk.getPublicKey(), issuer, clientId, leeway);
        } catch (SigningKeyNotFoundException e) {
            throw new AuthenticationServiceException("Could not retrieve jwks from issuer", e);
        } catch (InvalidPublicKeyException e) {
            throw new AuthenticationServiceException("Could not retrieve public key from issuer", e);
        } catch (JwkException e) {
            throw new AuthenticationServiceException("Cannot authenticate with jwt", e);
        }
    }

    private static JWTVerifier providerForRS256(RSAPublicKey publicKey, String issuer, String audience, long leeway) {
        return JWT.require(Algorithm.RSA256(publicKey, null))
                .withIssuer(issuer)
                .withAudience(audience)
                .acceptLeeway(leeway)
                .build();
    }

    private static JWTVerifier providerForHS256(byte[] secret, String issuer, String audience, long leeway) {
        return JWT.require(Algorithm.HMAC256(secret))
                .withIssuer(issuer)
                .withAudience(audience)
                .acceptLeeway(leeway)
                .build();
    }
}
