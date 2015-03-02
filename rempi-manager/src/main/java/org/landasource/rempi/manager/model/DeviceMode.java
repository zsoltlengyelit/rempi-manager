package org.landasource.rempi.manager.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Formula;
import org.landasource.rempi.manager.core.gpio.GpioPin;

/**
 *
 * @author Zsolti
 *
 */
@Entity
@Table(name = "device_mode", uniqueConstraints = @UniqueConstraint(columnNames = "device_id", name = "unique_for_device"))
public class DeviceMode implements Serializable {

	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 8364559461495008991L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "device_id")
	private Device device;

	@Formula("device_id")
	private Long deviceId;

	/**
	 * Operation modes.
	 */
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	@Enumerated(EnumType.STRING)
	@MapKeyColumn(name = "gpioPin", length = 50)
	@Column(name = "gpioPinMode", length = 100, nullable = false)
	@CollectionTable(name = "operationModes", joinColumns = @JoinColumn(name = "device_mode_id"))
	private Map<GpioPin, OperationMode> operationModes;

	public DeviceMode(final Device setDevice) {
		this();
		this.device = setDevice;
		this.deviceId = setDevice.getId();
	}

	public DeviceMode() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(final Device device) {
		this.device = device;
	}

	public Map<GpioPin, OperationMode> getOperationModes() {
		if (null == operationModes) {
			operationModes = new HashMap<GpioPin, OperationMode>();
		}
		return operationModes;
	}

	public void setOperationModes(final Map<GpioPin, OperationMode> operationModes) {
		this.operationModes = operationModes;
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
		result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
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
		final DeviceMode other = (DeviceMode) obj;
		if (getDeviceId() == null) {
			if (other.getDeviceId() != null) {
				return false;
			}
		} else if (!getDeviceId().equals(other.getDeviceId())) {
			return false;
		}
		return true;
	}

}
