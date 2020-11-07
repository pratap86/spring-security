package com.pratap.springcloud.security.oath2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "couponservice";
	private static final String GET_PATTERNS = "/couponapi/coupons/{code:^[A-Z]*$}";
	private static final String POST_PATTERNS = "/couponapi/coupons";
	

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(RESOURCE_ID);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.mvcMatchers(HttpMethod.GET, GET_PATTERNS).hasAnyRole("USER", "ADMIN")
		.mvcMatchers(HttpMethod.POST, POST_PATTERNS).hasRole("ADMIN")
		.anyRequest().denyAll().and().csrf().disable();
	}
}
