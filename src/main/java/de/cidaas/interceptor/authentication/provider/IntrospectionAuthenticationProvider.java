package de.cidaas.interceptor.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.jwt.JWTValidation;
import de.cidaas.jwt.exceptions.JWTVerificationException;
import de.cidaas.jwt.exceptions.TokenExpiredException;
import de.cidaas.model.JwtAuthentication;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider {
	
	private final String clientId;
	private final String issuer;
	private final String clientSecret;
	
	public IntrospectionAuthenticationProvider(String clientId, String issuer, String clientSecret) {
		this.clientId = clientId;
		this.issuer = issuer;
		this.clientSecret = clientSecret;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		try {
			JwtAuthentication jwtAuth = (JwtAuthentication) authentication;
			String token = jwtAuth.getCredentials().getTokenAsString();
			
			boolean isActive = JWTValidation
								.getInstance()
								.validateWithIntrospection(token, "access_token", clientId, clientSecret, issuer)
								.isActive();
			
			if (isActive) {
				jwtAuth.setAuthenticated(true);
				return jwtAuth;
			} else {
				throw new TokenExpiredException("Token not activ!");
			}
			
		}catch(JWTVerificationException e) {
			throw new AuthenticationServiceException("Failed to verify token!", e);
		}
	}
}
