package org.landasource.rempi.controller.devicetype;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Zsolti
 *
 */
public class DeviceTypeForm {

	@NotNull
	@NotEmpty
	private String name;

	private List<String> commands;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<String> getCommands() {
		if (null == commands) {
			commands = new ArrayList<String>();
		}
		return commands;
	}

	public void setCommands(final List<String> commands) {
		this.commands = commands;
	}

}
