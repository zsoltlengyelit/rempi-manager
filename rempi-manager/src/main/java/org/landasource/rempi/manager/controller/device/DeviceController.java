package org.landasource.rempi.manager.controller.device;

import org.landasource.rempi.manager.core.clientstate.GpioState;
import org.landasource.rempi.manager.core.clientstate.StateStore;
import org.landasource.rempi.manager.core.controller.CrudController;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceType;
import org.landasource.rempi.manager.repo.DeviceRepo;
import org.landasource.rempi.manager.repo.DeviceTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@Autowired
	private StateStore stateStore;

	@Override
	protected CrudRepository<Device, Long> getRepo() {
		return deviceRepo;
	}

	@Override
	protected String getControllerName() {
		return "device";
	}

	@RequestMapping(value = "/control/{id}", method = RequestMethod.GET)
	public String control(@PathVariable("id") final Long id, final ModelMap modelMap) {

		final Device device = deviceRepo.findOne(id);
		modelMap.addAttribute("device", device);

		final GpioState state = stateStore.getState(device.getSerial());
		modelMap.addAttribute("state", state);

		return "device/control";
	}

	@RequestMapping("control/{id}/gpio/{gpio}/{mode}")
	public String controlGpio(@PathVariable("id") final Long id, @PathVariable("gpio") final Integer gpio, @PathVariable("mode") final String mode, final ModelMap modelMap) {

		final Device device = deviceRepo.findOne(id);
		modelMap.addAttribute("device", device);

		final boolean enabled = "enable".equals(mode);
		stateStore.getState(device.getSerial()).getByGpio(gpio).setEnabled(enabled);

		final GpioState state = stateStore.getState(device.getSerial());
		modelMap.addAttribute("state", state);

		return "redirect:/device/control/" + id;
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
