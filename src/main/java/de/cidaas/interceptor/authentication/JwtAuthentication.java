package de.cidaas.interceptor.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import de.cidaas.jwt.JWT;
import de.cidaas.jwt.exceptions.JWTDecodeException;
import de.cidaas.jwt.interfaces.DecodedJWT;

@SuppressWarnings("serial")
public class JwtAuthentication implements Authentication {
	
	private static Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

	private final DecodedJWT token;
	private boolean authenticated;

	private JwtAuthentication(DecodedJWT token) {
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
	public Object getPrincipal() {
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
	
    public static JwtAuthentication usingToken(String token) {
        if (token == null) {
            logger.debug("No token was provided to build {}", JwtAuthentication.class.getName());
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            return new JwtAuthentication(jwt);
        } catch (JWTDecodeException e) {
            logger.debug("Failed to decode token as jwt", e);
            return null;
        }
    }
}
