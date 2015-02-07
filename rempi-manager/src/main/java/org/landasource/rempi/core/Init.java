package org.landasource.rempi.core;

import org.landasource.rempi.model.User;
import org.landasource.rempi.repo.UserRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zsolti
 *
 *
 */
@Component
public class Init implements InitializingBean {

	static @Logger org.slf4j.Logger LOGGER;

	private static final String ADMIN = "admin";

	@Autowired
	private UserRepo userRepo;

	@Override
	public void afterPropertiesSet() throws Exception {

		LOGGER.info("Check admin user.");

		if (userRepo.findByUsername(ADMIN) == null) {

			final User user = new User();
			user.setFullName(ADMIN);
			user.setUsername(ADMIN);
			user.setPasswordHash(PasswordUtil.makeHash(ADMIN));

			userRepo.save(user);
		}

	}
}
