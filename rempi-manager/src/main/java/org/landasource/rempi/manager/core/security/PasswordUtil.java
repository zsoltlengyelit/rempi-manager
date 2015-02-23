package org.landasource.rempi.manager.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Zsolti
 *
 */
public class PasswordUtil {

	/**
	 *
	 * @param password
	 * @return
	 */
	public static String makeHash(final String password) {

		final BCryptPasswordEncoder passwordEncoder = getEncoder();
		final String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public static BCryptPasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
}
