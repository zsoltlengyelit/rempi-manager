package org.landasource.rempi.manager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Zsolti
 *
 */
@Entity
@Table(name = "device_metadata")
public class DeviceMetadata implements Serializable {
	private static final long serialVersionUID = -338135208608539516L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String notes;

	private Date lastCheckin;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
	private Device device;

	@Formula("device_id")
	private Long deviceId;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(final String notes) {
		this.notes = notes;
	}

	public Date getLastCheckin() {
		return lastCheckin;
	}

	public void setLastCheckin(final Date lastCheckin) {
		this.lastCheckin = lastCheckin;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(final Device device) {
		this.device = device;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(final Long deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (Hibernate.getClass(this) != Hibernate.getClass(obj)) {
			return false;
		}
		final DeviceMetadata other = (DeviceMetadata) obj;
		if (deviceId == null) {
			if (other.deviceId != null) {
				return false;
			}
		} else if (!deviceId.equals(other.deviceId)) {
			return false;
		}
		return true;
	}

}
