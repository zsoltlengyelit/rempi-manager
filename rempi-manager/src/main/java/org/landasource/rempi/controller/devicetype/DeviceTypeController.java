package org.landasource.rempi.controller.devicetype;

import java.util.ArrayList;
import java.util.List;

import org.landasource.rempi.core.controller.CrudController;
import org.landasource.rempi.model.DeviceCommand;
import org.landasource.rempi.model.DeviceType;
import org.landasource.rempi.repo.DeviceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Zsolti
 *
 */
@Controller
@RequestMapping("deviceType")
public class DeviceTypeController extends CrudController<DeviceType, DeviceTypeForm> {

	@Autowired
	private DeviceTypeRepo deviceTypeRepo;

	@Override
	protected CrudRepository<DeviceType, Long> getRepo() {
		return deviceTypeRepo;
	}

	@Override
	protected void addFormParams(final ModelMap modelMap) {
		final List<Class<? extends DeviceCommand>> commands = new ArrayList<Class<? extends DeviceCommand>>();
		commands.add(DeviceCommand.class);
		modelMap.addAttribute("commands", commands);
	}

	@Override
	protected String getControllerName() {
		return "deviceType";
	}

	@Override
	protected void validateFormAtSave(final DeviceTypeForm form, final BindingResult result) {

	}

	@Override
	protected void validateFormAtUpdate(final Long id, final DeviceTypeForm form, final BindingResult result) {

	}

	@Override
	protected void fillForm(final DeviceTypeForm form, final DeviceType model) {
		form.setName(model.getName());

		for (final Class<? extends DeviceCommand> command : model.getCommands()) {
			form.getCommands().add(command.getCanonicalName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void fillModel(final DeviceTypeForm form, final DeviceType model) {

		model.setName(form.getName());
		try {
			for (final String commandName : form.getCommands()) {
				model.getCommands().add((Class<? extends DeviceCommand>) Class.forName(commandName));
			}
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}
}
