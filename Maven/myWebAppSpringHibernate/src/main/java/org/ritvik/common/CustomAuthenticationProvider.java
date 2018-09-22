package org.ritvik.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private Logger logger;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = null;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			logger.info("Key Value - " + authentication.getName());
			
			if (authentication.getName().contains("ritvik")) {
				token = new UsernamePasswordAuthenticationToken(new User(authentication.getName(), "", authorities), authentication.getCredentials(), new ArrayList<>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}