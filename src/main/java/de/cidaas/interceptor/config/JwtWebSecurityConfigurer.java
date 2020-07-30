package de.cidaas.interceptor.config;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import de.cidaas.interceptor.authentication.provider.OfflineAuthenticationProvider;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwk.JwkProviderBuilder;
import de.cidaas.jwt.constants.MessageConstants;

/**
 * Utility class for configuring Security for your Spring API
 */
public class JwtWebSecurityConfigurer {

    final AuthenticationProvider provider;

    private JwtWebSecurityConfigurer(AuthenticationProvider authenticationProvider) {
        this.provider = authenticationProvider;
    }

    /**
     * Configures application authorization for JWT signed with RS256.
     * Will try to validate the token using the public key downloaded from "$issuer/.well-known/jwks.json"
     * and matched by the value of {@code kid} of the JWT header
     * @param audience identifier of the API and must match the {@code aud} value in the token
     * @param issuer of the token for this API and must match the {@code iss} value in the token
     * @return JwtWebSecurityConfigurer for further configuration
     */
    public static JwtWebSecurityConfigurer offlineValidationForRS256(String audience, String issuer) {
        final JwkProvider jwkProvider = new JwkProviderBuilder(issuer).build();
        return new JwtWebSecurityConfigurer(new OfflineAuthenticationProvider(jwkProvider, issuer, audience));
    }

    /**
     * Configures application authorization for JWT signed with HS256
     * @param audience identifier of the API and must match the {@code aud} value in the token
     * @param issuer of the token for this API and must match the {@code iss} value in the token
     * @param secret used to sign and verify tokens encoded in Base64
     * @return JwtWebSecurityConfigurer for further configuration
     */
    public static JwtWebSecurityConfigurer offlineValidationForHS256(String audience, String issuer, String secret) {
        final byte[] secretBytes = new Base64(true).decode(secret);
        return new JwtWebSecurityConfigurer(new OfflineAuthenticationProvider(secretBytes, issuer, audience));
    }

    /**
     * Further configure the {@link HttpSecurity} object with some sensible defaults
     * by registering objects to obtain a bearer token from a request.
     * @param http configuration for Spring
     * @return the http configuration for further customizations
     * @throws Exception
     */
    public HttpSecurity configure(HttpSecurity http) throws Exception {
        return http
                .authenticationProvider(provider)
                .securityContext()
                .securityContextRepository(new BearerSecurityContextRepository())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
    }
}
