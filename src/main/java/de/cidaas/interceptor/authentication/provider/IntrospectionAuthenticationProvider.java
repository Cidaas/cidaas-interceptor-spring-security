package de.cidaas.interceptor.authentication.provider;

import org.apache.http.HttpHost;
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
	private final OpenIdConfigurationLoader openIdConfigLoader;
	
	public IntrospectionAuthenticationProvider(String clientId, String issuer, JWTValidation jwtValidation) {
		this.clientId = clientId;
		this.issuer = issuer;
		this.jwtValidation = jwtValidation;
		this.openIdConfigLoader = OpenIdConfigurationLoader.getInstance();
	}
	
	public void setUseSystemProperties(boolean useSystemProperties) {
		openIdConfigLoader.setUseSystemProperties(useSystemProperties);
		jwtValidation.setUseSystemProperties(useSystemProperties);
	}
	
	public void setProxy(final String hostname, final int port, final String scheme) {
		final HttpHost proxy = new HttpHost(hostname, port, scheme);
		
		openIdConfigLoader.setProxy(proxy);
		jwtValidation.setProxy(proxy);
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
			String introspectionURI = openIdConfigLoader.getIntrospectionURL(issuer);
			
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
