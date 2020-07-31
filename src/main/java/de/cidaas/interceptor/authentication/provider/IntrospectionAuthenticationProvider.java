package de.cidaas.interceptor.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.interceptor.authentication.PreAuthenticatedAuthenticationJsonWebToken;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider{
		
	private final String clientId;
	private final String issuer;
	private final String clientSecret;
	 
	
    public IntrospectionAuthenticationProvider(String clientId, String issuer, String clientSecret) {
    	this.clientId = clientId;
        this.issuer = issuer;
        this.clientSecret = clientSecret;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationJsonWebToken.class.equals(authentication);
	}

}
