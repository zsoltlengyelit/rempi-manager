package org.landasource.rempi.controller.device;

import org.landasource.rempi.core.controller.CrudController;
import org.landasource.rempi.model.Device;
import org.landasource.rempi.model.DeviceType;
import org.landasource.rempi.repo.DeviceRepo;
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
@RequestMapping("/device")
public class DeviceController extends CrudController<Device, DeviceForm> {

	@Autowired
	private DeviceRepo deviceRepo;
	@Autowired
	private DeviceTypeRepo deviceTypeRepo;

	@Override
	protected CrudRepository<Device, Long> getRepo() {
		return deviceRepo;
	}

	@Override
	protected String getControllerName() {
		return "device";
	}

	@Override
	protected void validateFormAtSave(final DeviceForm form, final BindingResult result) {

		final Device device = deviceRepo.findBySerial(form.getSerial());
		if (null != device) {
			result.reject("serial-exist", "Serial must be unique");
		}
	}

	@Override
	protected void validateFormAtUpdate(final Long id, final DeviceForm form, final BindingResult result) {
		final Device device = deviceRepo.findBySerial(form.getSerial());
		if (null != device && !device.getId().equals(id)) {
			result.reject("serial-exist", "Serial must be unique");
		}
	}

	@Override
	protected void fillForm(final DeviceForm form, final Device model) {
		form.setName(model.getName());
		form.setSerial(model.getSerial());
		form.setDeviceTypeId(model.getDeviceType().getId());
	}

	@Override
	protected void fillModel(final DeviceForm form, final Device model) {
		model.setName(form.getName());
		model.setSerial(form.getSerial());
		model.setDeviceType(deviceTypeRepo.findOne(form.getDeviceTypeId()));
	}

	@Override
	protected void addFormParams(final ModelMap modelMap) {
		final Iterable<DeviceType> types = deviceTypeRepo.findAll();
		modelMap.addAttribute("deviceTypes", types);

	}

}
