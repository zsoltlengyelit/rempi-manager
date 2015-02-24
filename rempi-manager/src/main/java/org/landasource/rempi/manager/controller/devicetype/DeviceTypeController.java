package org.landasource.rempi.manager.controller.devicetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.landasource.rempi.manager.core.controller.CrudController;
import org.landasource.rempi.manager.model.DeviceCommand;
import org.landasource.rempi.manager.model.DeviceType;
import org.landasource.rempi.manager.repo.DeviceTypeRepo;
import org.reflections.Reflections;
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
@RequestMapping("admin/deviceType")
public class DeviceTypeController extends CrudController<DeviceType, DeviceTypeForm> {

	@Autowired
	private DeviceTypeRepo deviceTypeRepo;

	@Override
	protected boolean isAdminController() {
		return true;
	}

	@Override
	protected CrudRepository<DeviceType, Long> getRepo() {
		return deviceTypeRepo;
	}

	@Override
	protected void addFormParams(final ModelMap modelMap) {
		final List<Class<? extends DeviceCommand>> commands = new ArrayList<Class<? extends DeviceCommand>>();
		final Set<Class<? extends DeviceCommand>> subTypes = new Reflections("org.landasource.rempi").getSubTypesOf(DeviceCommand.class);
		commands.addAll(subTypes);

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

		model.getCommands().clear();
		try {
			for (final String commandName : form.getCommands()) {
				model.getCommands().add((Class<? extends DeviceCommand>) Class.forName(commandName));
			}
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}
}
