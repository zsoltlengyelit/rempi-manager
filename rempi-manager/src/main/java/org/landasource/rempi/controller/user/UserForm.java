package org.landasource.rempi.controller.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Zsolti
 *
 */
public class UserForm {

	@NotNull
	@NotEmpty
	private String username;

	@NotNull
	@NotEmpty
	private String password;

	@NotNull
	@NotEmpty
	private String fullName;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

}
