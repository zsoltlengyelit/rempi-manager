package org.landasource.rempi.core;

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

		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
}
