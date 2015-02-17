package org.landasource.rempi.manager.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.NotEmpty;
import org.landasource.rempi.common.GpioPin;

/**
 * GPIO wirin of devices.
 */
@Entity
@Table(name = "wiring", uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "unique_name"))
public class Wiring implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -677541632229747102L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	@ElementCollection
	@MapKeyColumn(name = "gpioPin", length = 50)
	@Column(name = "gpioPinName", length = 100, nullable = false)
	@CollectionTable(name = "pintables", joinColumns = @JoinColumn(name = "wiring_id"))
	private Map<GpioPin, String> pinTable;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Map<GpioPin, String> getPinTable() {
		if (null == pinTable) {
			pinTable = new HashMap<GpioPin, String>();
		}
		return pinTable;
	}

	public void setPinTable(final Map<GpioPin, String> pinTable) {
		this.pinTable = pinTable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
		final Wiring other = (Wiring) obj;
		if (getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!getName().equals(other.getName())) {
			return false;
		}
		return true;
	}

}
