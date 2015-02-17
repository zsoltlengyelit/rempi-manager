package org.landasource.rempi.manager.controller.wiring;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Zsolti
 *
 */
public class WiringForm {

	@NotNull
	@NotEmpty
	private String name;

	private Map<String, String> pinTable;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Map<String, String> getPinTable() {
		if (null == pinTable) {
			pinTable = new HashMap<String, String>();
		}
		return pinTable;
	}

	public void setPinTable(final Map<String, String> pinTable) {
		this.pinTable = pinTable;
	}

}
