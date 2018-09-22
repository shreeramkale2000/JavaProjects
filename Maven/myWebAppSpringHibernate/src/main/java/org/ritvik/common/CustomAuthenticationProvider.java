package org.ritvik.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = Logger.getLogger("org.ritvik.processLog");

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = null;
		try {
			logger.info("Key Value - " + authentication.getName());
			if (authentication.getName().contains("ritvik")) {
				token = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), new ArrayList<>());
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