package com.pratap.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.pratap.spring.security.authenrication.MyAuthenticationProvider;

@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//		UserDetails user = User.withUsername("test").password(passwordEncoder.encode("test123")).authorities("read").build();
//		userDetailsService.createUser(user);
//		
//		auth.userDetailsService(userDetailsService);
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests().anyRequest().authenticated();
	}
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
