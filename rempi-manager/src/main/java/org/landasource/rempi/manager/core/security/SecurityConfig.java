package org.landasource.rempi.manager.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {

		final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);

		daoAuthenticationProvider.setPasswordEncoder(PasswordUtil.getEncoder());

		auth.authenticationProvider(daoAuthenticationProvider);

		// @formatter:off
		// auth.eraseCredentials(true);
		// @formatter:on
		// super.configure(auth);
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable();
		// http.securityContext().disable();

		// @formatter:off
		http.authorizeRequests()
		.antMatchers("/").access("hasRole('ADMIN') or hasRole('CUSTOMER')")
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/device/**").access("hasRole('ADMIN') or hasRole('CUSTOMER')")
		.antMatchers("/user/**").access("hasRole('ADMIN')")
		.antMatchers("/wiring/**").access("hasRole('ADMIN')")
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
		// @formatter:on
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		super.configure(web);
	}
}