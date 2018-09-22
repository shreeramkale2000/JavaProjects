package org.ritvik.common;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(rawPassword.toString());
	}

}
