package de.cidaas.interceptor.authentication.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import de.cidaas.interceptor.authentication.JwtAuthentication;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider{

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
