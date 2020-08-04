package de.cidaas.interceptor.authentication.provider;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.BadCredentialsException;

import de.cidaas.interceptor.TokenHelperTest;
import de.cidaas.interceptor.authentication.JwtAuthentication;
import de.cidaas.jwk.Jwk;
import de.cidaas.jwk.JwkException;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwt.JWT;
import de.cidaas.jwt.interfaces.DecodedJWT;

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
}
