package org.landasource.rempi.manager.controller.device;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.landasource.rempi.manager.api.http.BadRequestException;
import org.landasource.rempi.manager.core.clientstate.DeviceManager;
import org.landasource.rempi.manager.core.controller.CrudController;
import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceMode;
import org.landasource.rempi.manager.model.DeviceType;
import org.landasource.rempi.manager.model.OperationMode;
import org.landasource.rempi.manager.model.Wiring;
import org.landasource.rempi.manager.repo.DeviceRepo;
import org.landasource.rempi.manager.repo.DeviceTypeRepo;
import org.landasource.rempi.manager.repo.WiringRepo;
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
	private DeviceManager deviceManager;

	@Autowired
	private WiringRepo wiringRepo;

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

		final DeviceMode deviceMode = deviceManager.getDeviceMode(device);
		modelMap.addAttribute("deviceOperationModes", deviceMode.getOperationModes());
		modelMap.addAttribute("operationModes", OperationMode.values());

		final List<GpioPin> namedPins = new ArrayList<GpioPin>(device.getWiring().getPinTable().keySet());
		Collections.sort(namedPins, new Comparator<GpioPin>() {

			@Override
			public int compare(final GpioPin o1, final GpioPin o2) {
				final String n1 = device.getWiring().getPinTable().get(o1);
				final String n2 = device.getWiring().getPinTable().get(o2);
				return n1.compareToIgnoreCase(n2);
			}
		});

		modelMap.addAttribute("namedPins", namedPins);

		return "device/control";
	}

	@RequestMapping("control/{id}/gpio/{gpio}/{mode}")
	public String controlGpio(@PathVariable("id") final Long id, @PathVariable("gpio") final Integer gpio, @PathVariable("mode") final OperationMode mode, final ModelMap modelMap) {

		final Device device = deviceRepo.findOne(id);
		if (null == device) {
			throw new BadRequestException();
		}
		final GpioPin gpioPin = GpioPin.byIndex(gpio);

		deviceManager.setMode(device, gpioPin, mode);

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
		form.setWiringId(model.getWiring().getId());
	}

	@Override
	protected void fillModel(final DeviceForm form, final Device model) {
		model.setName(form.getName());
		model.setSerial(form.getSerial());
		model.setDeviceType(deviceTypeRepo.findOne(form.getDeviceTypeId()));
		model.setWiring(wiringRepo.findOne(form.getWiringId()));
	}

	@Override
	protected void addFormParams(final ModelMap modelMap) {
		final Iterable<DeviceType> types = deviceTypeRepo.findAll();
		modelMap.addAttribute("deviceTypes", types);

		final Iterable<Wiring> wirings = wiringRepo.findAll();
		modelMap.addAttribute("wirings", wirings);

	}

}
