package de.cidaas.interceptor.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

//This will ensure that this Initializer will be called before any other 
//Initializer with the default value Ordered.LOWEST_PRECEDENCE
//If this is not ensured, spring boot might have a race condition problem when registration the bean springSecurityFilterChain
//https://stackoverflow.com/questions/28306031/apparent-spring-boot-race-condition-causing-duplicate-springsecurityfilterchain
@Order(-500)
public class SecurityWebApplicationInitializer 
  extends AbstractSecurityWebApplicationInitializer {
    // This will simply register the springSecurityFilterChain Filter for every URL in the application
	// This way the class {@BearerSecurityContextRepository}, {@IntrospectionAuthenticationProvider} and {@OfflineAuthenticationProvider} are invoked
}
