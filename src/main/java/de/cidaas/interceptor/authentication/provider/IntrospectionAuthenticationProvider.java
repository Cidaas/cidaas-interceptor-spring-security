package de.cidaas.interceptor.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.jwt.JWTValidation;
import de.cidaas.jwt.TokenType;
import de.cidaas.jwt.exceptions.TokenExpiredException;
import de.cidaas.jwt.helper.OpenIdConfigurationLoader;
import de.cidaas.model.JwtAuthentication;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider {
	
	private final String clientId;
	private final String issuer;
	private final JWTValidation jwtValidation;
	
	public IntrospectionAuthenticationProvider(String clientId, String issuer, JWTValidation jwtValidation) {
		this.clientId = clientId;
		this.issuer = issuer;
		this.jwtValidation = jwtValidation;
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
			String introspectionURI = OpenIdConfigurationLoader.getInstance().getIntrospectionURL(issuer);
			
			boolean isActive = jwtValidation
								.validateWithIntrospection(token, TokenType.ACCESS.typeHint, clientId, introspectionURI)
								.isActive();
			
			if (isActive) {
				jwtAuth.setAuthenticated(true);
				return jwtAuth;
			} else {
				throw new TokenExpiredException("Token not active!");
			}
			
		}catch(Exception e) {
			throw new AuthenticationServiceException("Failed to verify token!", e);
		}
	}
}
