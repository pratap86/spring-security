package com.pratap.spring.security.authenrication;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * 
 * Custom AuthenticationProvider, by using this get rid off provide UserDetailsService & PasswordEncoder
 * As we do that {@link com.pratap.spring.security.config.MySecurityConfig}
 * @author Pratap Narayan
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(MyAuthenticationProvider.class);

	/**
	 * @param authentication the authentication request object.
	 * 
	 * @return usernamePasswordAuthenticationToken the authentication object
	 * 
	 * @throws BadCredentialsException, if User or Password invalid
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		logger.info("MyAuthenticationProvider.authenticate() start..");
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if("test".equals(name) && "test123".equals(password)) {
			return new UsernamePasswordAuthenticationToken(name, password, Arrays.asList());
		} else {
			throw new BadCredentialsException("User or Password invalid");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		logger.info("MyAuthenticationProvider.supports() start..");
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
