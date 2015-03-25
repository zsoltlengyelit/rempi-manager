package org.landasource.rempi.manager.controller.device;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Zsolti
 *
 */
public class DeviceForm {

	@NotEmpty
	@NotNull
	private String name;

	@NotEmpty
	@NotNull
	private String serial;

	@NotNull
	private Long deviceTypeId;

	@NotNull
	private Long wiringId;

	private String notes;

	private boolean active;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(final String serial) {
		this.serial = serial;
	}

	public Long getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(final Long deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public Long getWiringId() {
		return wiringId;
	}

	public void setWiringId(final Long wiringId) {
		this.wiringId = wiringId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}

}
