package de.cidaas.interceptor.authentication.provider;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.cidaas.model.IntrospectionRequest;
import de.cidaas.model.IntrospectionResponse;
import de.cidaas.model.JwtAuthentication;

public class IntrospectionAuthenticationProvider implements AuthenticationProvider {
	
	private final String clientId;
	private final String issuer;
	private final String clientSecret;
	
	private final ObjectMapper objectMapper;

	public IntrospectionAuthenticationProvider(String clientId, String issuer, String clientSecret) {
		this.clientId = clientId;
		this.issuer = issuer;
		this.clientSecret = clientSecret;
		this.objectMapper = new ObjectMapper();
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.equals(authentication);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthentication jwtAuth = (JwtAuthentication) authentication;
		
		IntrospectionRequest requestInfo = new IntrospectionRequest(jwtAuth.getCredentials().getTokenAsString(), "access_token");
		requestInfo.setClient_id(clientId);
		requestInfo.setClient_secret(clientSecret);
		
		IntrospectionResponse tokenValidationResp = validateTokenInRemote(requestInfo);

		if (tokenValidationResp.isActive()) {
			jwtAuth.setAuthenticated(true);
			return jwtAuth;
		} else {
			throw new AuthenticationServiceException("Failed to verify token!");
		}
	}
	
	private IntrospectionResponse validateTokenInRemote(IntrospectionRequest tokenInfo){
		try {
			HttpPost request = new HttpPost(new URI("https://nightlybuild.cidaas.de/token-srv/introspect"));
			request.setEntity(new StringEntity(objectMapper.writeValueAsString(tokenInfo)));
			request.addHeader(HTTP.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			
			IntrospectionResponse introspectionResponse = new IntrospectionResponse();
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				introspectionResponse = objectMapper.readValue(response.getEntity().getContent(), IntrospectionResponse.class);
			} 
			return introspectionResponse;
		} catch (Exception e) {
			throw new AuthenticationServiceException("Error during request to the introspection endpoint", e);
		}
	}
}
