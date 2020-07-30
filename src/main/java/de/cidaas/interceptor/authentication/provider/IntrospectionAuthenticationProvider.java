package de.cidaas.interceptor.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.interceptor.authentication.JwtAuthentication;
import de.cidaas.jwk.JwkProvider;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider{
	
	 private final String clientSecret;
	 private final String issuer;
	 private final String audience;
	
    public IntrospectionAuthenticationProvider(String clientSecret, String issuer, String audience) {
        this.clientSecret = clientSecret;
        this.issuer = issuer;
        this.audience = audience;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.isAssignableFrom(authentication);
	}

}
