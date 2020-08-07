package de.cidaas.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import de.cidaas.jwt.interfaces.DecodedJWT;

@SuppressWarnings("serial")
public class JwtAuthentication implements Authentication {
	
	private final DecodedJWT token;
	private boolean authenticated;

	public JwtAuthentication(DecodedJWT token) {
		this.token = token;
		this.authenticated = false;
	}

	@Override
	public DecodedJWT getCredentials() {
		return token;
	}

	@Override
	public DecodedJWT getDetails() {
		return null;
	}

	@Override
	public String getPrincipal() {
		return token.getSubject();
	}

	@Override
	public String getName() {
		return token.getSubject();
	}
	
	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) {
		this.authenticated = isAuthenticated;
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		final String[] scopes = token.getClaim("scopes").asArray(String.class);
		if (scopes != null && scopes.length > 0) {
			for (String value : scopes) {
				authorities.add(new SimpleGrantedAuthority(value));
			}
		}				
		
		final String[] roles = token.getClaim("roles").asArray(String.class);
		if (roles != null && roles.length > 0) {
			for (String value : roles) {
				authorities.add(new SimpleGrantedAuthority("ROLE_"+value));
			}
		}
		
		return authorities;
	}
}
