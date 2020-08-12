package de.cidaas.interceptor.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer 
  extends AbstractSecurityWebApplicationInitializer {
    // This will simply register the springSecurityFilterChain Filter for every URL in the application
	// This way the class {@BearerSecurityContextRepository}, {@IntrospectionAuthenticationProvider} and {@OfflineAuthenticationProvider} are invoked
}
