package org.landasource.rempi.manager.controller.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.landasource.rempi.manager.core.form.CreateValidation;

/**
 *
 * @author Zsolti
 *
 */
public class UserForm {

	@NotNull
	@NotEmpty
	private String username;

	@NotNull(groups = CreateValidation.class)
	@NotEmpty(groups = CreateValidation.class)
	private String password;

	@NotNull
	@NotEmpty
	private String fullName;

	private boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

}
