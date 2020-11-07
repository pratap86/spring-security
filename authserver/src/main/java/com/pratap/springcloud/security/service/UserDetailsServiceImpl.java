package com.pratap.springcloud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pratap.springcloud.security.entities.UserEntity;
import com.pratap.springcloud.security.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(username);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found for email "+username);
		}
		return new User(userEntity.getEmail(), userEntity.getPassword(), userEntity.getRoles());
	}

}
