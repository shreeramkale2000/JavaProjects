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

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private Logger logger;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String Key = null;
		String[] values = null;
		CustomUser customUser = null;
		List<GrantedAuthority> authorities = null;
		UsernamePasswordAuthenticationToken token = null;
		try {
			Key = authentication.getName();
			logger.info("Key Value - " + Key);
			values = Key.split(",");
			
			if (values.length >= 3) {
				authorities = new ArrayList<GrantedAuthority>();
				customUser = new CustomUser(values[0], "", authorities);
				customUser.setFirstName(values[1]);
				customUser.setLastName(values[2]);
				token = new UsernamePasswordAuthenticationToken(customUser, "", authorities);
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