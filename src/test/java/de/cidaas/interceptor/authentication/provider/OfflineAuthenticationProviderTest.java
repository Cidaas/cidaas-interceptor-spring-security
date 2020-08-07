package de.cidaas.interceptor.authentication.provider;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;

import de.cidaas.interceptor.TokenHelperTest;
import de.cidaas.jwk.Jwk;
import de.cidaas.jwk.JwkException;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwt.JWT;
import de.cidaas.jwt.interfaces.DecodedJWT;
import de.cidaas.model.JwtAuthentication;

@RunWith(MockitoJUnitRunner.class)
public class OfflineAuthenticationProviderTest {

	@Mock
	JwkProvider provider;

	OfflineAuthenticationProvider authenticationProvider = null;
	JwtAuthentication jwtAuth = null;
	
	@Test
	@SuppressWarnings("deprecation")
	public void testAuthenticatenWithValidToken() throws JwkException {
		authenticationProvider = new OfflineAuthenticationProvider(TokenHelperTest.getClientId(), TokenHelperTest.getIssuer(), provider);
		DecodedJWT jwt = JWT.decode(TokenHelperTest.getValidToken());
		jwtAuth = new JwtAuthentication(jwt);
		
		when(provider.get(jwt.getKeyId())).thenReturn(new Jwk(TokenHelperTest.getKId(), "RSA", "RS256", "sig", "", "", null, "", TokenHelperTest.getPublicKeyAsHashMap()));

		assertNotNull(authenticationProvider.authenticate(jwtAuth));
	}
	
	@Test(expected = BadCredentialsException.class)
	@SuppressWarnings("deprecation")
	public void testAuthenticatenWithExpiredToken() throws JwkException {
		authenticationProvider = new OfflineAuthenticationProvider(TokenHelperTest.getClientId(), TokenHelperTest.getIssuer(), provider);
		DecodedJWT jwt = JWT.decode(TokenHelperTest.getExpiredToken());
		jwtAuth = new JwtAuthentication(jwt);
		
		when(provider.get(jwt.getKeyId())).thenReturn(new Jwk(TokenHelperTest.getKId(), "RSA", "RS256", "sig", "", "", null, "", TokenHelperTest.getPublicKeyAsHashMap()));

		assertNotNull(authenticationProvider.authenticate(jwtAuth));
	}
	
	@Test(expected = AuthenticationServiceException.class)
	@SuppressWarnings("deprecation")
	public void testAuthenticatenWithInvalidPublicKey() throws JwkException {
		authenticationProvider = new OfflineAuthenticationProvider(TokenHelperTest.getClientId(), TokenHelperTest.getIssuer(), provider);
		DecodedJWT jwt = JWT.decode(TokenHelperTest.getExpiredToken());
		jwtAuth = new JwtAuthentication(jwt);
		
		HashMap<String, Object> attribues = TokenHelperTest.getPublicKeyAsHashMap();
		attribues.put("n", "invalid_value");
		
		when(provider.get(jwt.getKeyId())).thenReturn(new Jwk(TokenHelperTest.getKId(), "RSA", "RS256", "sig", "", "", null, "", attribues));

		assertNotNull(authenticationProvider.authenticate(jwtAuth));
	}
	
	@Test(expected = BadCredentialsException.class)
	public void testGetPublicKeyForKIDWhereKidIsNull() {
		authenticationProvider = new OfflineAuthenticationProvider(TokenHelperTest.getClientId(), TokenHelperTest.getIssuer(), provider);
		authenticationProvider.getPublicKeyForKID(null);
	}
	
	@Test(expected = AuthenticationServiceException.class)
	public void testGetPublicKeyForKIDWhereProviderIsNull() {
		authenticationProvider = new OfflineAuthenticationProvider(TokenHelperTest.getClientId(), TokenHelperTest.getIssuer(), null);
		authenticationProvider.getPublicKeyForKID(TokenHelperTest.getKId());
	}
}
