package de.cidaas.interceptor.authentication.provider;

import java.security.interfaces.RSAPublicKey;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.jwk.InvalidPublicKeyException;
import de.cidaas.jwk.Jwk;
import de.cidaas.jwk.JwkException;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwk.SigningKeyNotFoundException;
import de.cidaas.jwt.JWT;
import de.cidaas.jwt.JWTVerifier;
import de.cidaas.jwt.algorithms.Algorithm;
import de.cidaas.jwt.exceptions.JWTVerificationException;
import de.cidaas.model.JwtAuthentication;

public class OfflineAuthenticationProvider implements AuthenticationProvider {

    private final String issuer;
    private final String clientId;
    private final JwkProvider jwkProvider;

    public OfflineAuthenticationProvider(String clientId, String issuer, JwkProvider jwkProvider) {
    	this.clientId = clientId;
    	this.issuer = issuer;
    	this.jwkProvider = jwkProvider;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
        	JwtAuthentication jwtAuth = (JwtAuthentication) authentication;
        	
        	RSAPublicKey publicKey = getPublicKeyForKID(jwtAuth.getCredentials().getKeyId());
        	
        	JWTVerifier jwtVerifier = jwtVerifier(publicKey);
        	jwtVerifier.verify(jwtAuth.getCredentials().getTokenAsString());
        	
        	jwtAuth.setAuthenticated(true);
            return jwtAuth;
        } catch (JWTVerificationException e) {
            throw new BadCredentialsException("Not a valid token", e);
        }
    }
    
    public RSAPublicKey getPublicKeyForKID(String kid) {
        if (kid == null) {
            throw new BadCredentialsException("No kid found in jwt");
        }
        
        if (jwkProvider == null) {
            throw new AuthenticationServiceException("Missing jwk provider");
        }
        
		try {
			Jwk jwk = jwkProvider.get(kid);
			return (RSAPublicKey) jwk.getPublicKey();
        } catch (SigningKeyNotFoundException e) {
            throw new AuthenticationServiceException("Could not retrieve jwks from issuer", e);
        } catch (InvalidPublicKeyException e) {
            throw new AuthenticationServiceException("Could not retrieve public key from issuer", e);
        } catch (JwkException e) {
            throw new AuthenticationServiceException("Cannot authenticate with jwt", e);
        }
    }

    private JWTVerifier jwtVerifier(RSAPublicKey publicKey) throws AuthenticationException {
        return JWT.require(Algorithm.RSA256(publicKey, null))
        		.withIssuer(issuer)
        		.withAudience(clientId)
        		.build();
    }
}
