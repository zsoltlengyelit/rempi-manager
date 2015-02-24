package org.landasource.rempi.manager.core;

import org.landasource.rempi.manager.core.security.PasswordUtil;
import org.landasource.rempi.manager.core.security.Roles;
import org.landasource.rempi.manager.model.Role;
import org.landasource.rempi.manager.model.User;
import org.landasource.rempi.manager.repo.RoleRepo;
import org.landasource.rempi.manager.repo.UserRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

	private static final String USER = "user";

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	@Transactional
	public void afterPropertiesSet() throws Exception {

		LOGGER.info("Check admin user.");

		for (final Roles roles : Roles.values()) {
			if (null == roleRepo.findByName(roles.name())) {
				final Role newRole = new Role(roles.name());
				roleRepo.save(newRole);
			}
		}

		if (userRepo.findByUsername(ADMIN) == null) {

			final User admin = new User();
			admin.setFullName(ADMIN);
			admin.setUsername(ADMIN);
			admin.setPassword(PasswordUtil.makeHash(ADMIN));
			admin.setEnabled(true);
			admin.getRoles().add(roleRepo.findByName(Roles.ADMIN.name()));
			userRepo.save(admin);
		}

		if (userRepo.findByUsername(USER) == null) {

			final User user = new User();
			user.setFullName(USER);
			user.setUsername(USER);
			user.setPassword(PasswordUtil.makeHash(USER));
			user.setEnabled(false);
			user.getRoles().add(roleRepo.findByName(Roles.CUSTOMER.name()));
			userRepo.save(user);
		}

	}
}
