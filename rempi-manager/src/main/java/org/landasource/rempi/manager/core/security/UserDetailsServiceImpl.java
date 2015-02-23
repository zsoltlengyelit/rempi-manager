package org.landasource.rempi.manager.core.security;

import java.util.ArrayList;
import java.util.Collection;

import org.landasource.rempi.manager.model.Role;
import org.landasource.rempi.manager.model.User;
import org.landasource.rempi.manager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final User user = userRepo.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return buildUserFromUserEntity(user);
	}

	private org.springframework.security.core.userdetails.User buildUserFromUserEntity(final User user) {

		final String username = user.getUsername();
		final String password = user.getPassword();
		final boolean enabled = user.isEnabled();
		final boolean accountNonExpired = user.isEnabled();
		final boolean credentialsNonExpired = user.isEnabled();
		final boolean accountNonLocked = user.isEnabled();

		final Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (final Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		final org.springframework.security.core.userdetails.User credential = new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

		return credential;
	}
}
