package org.landasource.rempi.manager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Zsolti
 *
 */
@Entity
@Table(name = "device_type")
public class DeviceType implements Serializable {

	private static final long serialVersionUID = -6473681553244785479L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	@ElementCollection
	@CollectionTable(name = "device_type_commands", joinColumns = @JoinColumn(name = "device_type_id"))
	@Column(name = "type")
	private List<Class<? extends DeviceCommand>> commands;

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

	public List<Class<? extends DeviceCommand>> getCommands() {
		if (null == commands) {
			commands = new ArrayList<Class<? extends DeviceCommand>>();
		}
		return commands;
	}

	public void setCommands(final List<Class<? extends DeviceCommand>> commands) {
		this.commands = commands;
	}

}
