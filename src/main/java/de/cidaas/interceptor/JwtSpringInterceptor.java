package de.cidaas.interceptor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import de.cidaas.interceptor.authentication.provider.IntrospectionAuthenticationProvider;
import de.cidaas.interceptor.authentication.provider.OfflineAuthenticationProvider;
import de.cidaas.interceptor.config.BearerSecurityContextRepository;
import de.cidaas.interceptor.config.JwtAccessDeniedHandler;
import de.cidaas.interceptor.config.JwtAuthenticationEntryPoint;
import de.cidaas.jwk.JwkProvider;
import de.cidaas.jwk.JwkProviderBuilder;
import de.cidaas.jwt.JWTValidation;

/**
 * Utility class for configuring Security for your Spring API
 */
public class JwtSpringInterceptor {

    final AuthenticationProvider provider;

    private JwtSpringInterceptor(AuthenticationProvider authenticationProvider) {
        this.provider = authenticationProvider;
    }

    /**
     * Configures application authorization for JWT signed with RS256.
     * Will try to validate the token using the public key downloaded from "$issuer/.well-known/jwks.json"
     * and matched by the value of {@code kid} of the JWT header
     * @param clientId identifier of the API and must match the {@code aud} value in the token
     * @param issuer of the token for this API and must match the {@code iss} value in the token
     * @return JwtWebSecurityConfigurer for further configuration
     */
    public static JwtSpringInterceptor offlineValidation(String clientId, String issuer) {
        final JwkProvider jwkProvider = new JwkProviderBuilder(issuer).build();
        return new JwtSpringInterceptor(new OfflineAuthenticationProvider(clientId, issuer, jwkProvider));
    }

    /**
     * Configures application authorization with the introspection API
     * @param clientId identifier of the API and must match the {@code aud} value in the token
     * @param issuer of the token for this API and must match the {@code iss} value in the token
     * @param clientSecret used to identify the client
     * @return JwtWebSecurityConfigurer for further configuration
     */
    public static JwtSpringInterceptor introspectionValidation(String clientId, String issuer, String clientSecret) {
    	final JWTValidation jwtValidation = new JWTValidation();
        return new JwtSpringInterceptor(new IntrospectionAuthenticationProvider(clientId, issuer, jwtValidation));
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
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .exceptionHandling().accessDeniedHandler(new JwtAccessDeniedHandler())
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
    }
}
