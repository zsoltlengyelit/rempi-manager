package org.landasource.rempi.manager.core.clientstate;

import java.io.Serializable;

import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceMode;
import org.landasource.rempi.manager.model.OperationMode;
import org.landasource.rempi.manager.repo.DeviceModeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zsolti
 *
 */
@Component("deviceManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DeviceManager implements Serializable {

	/** */
	private static final long serialVersionUID = -5045579859695346792L;

	@Autowired
	private DeviceModeRepo deviceModeRepo;

	/**
	 *
	 * @param device
	 * @param gpioPin
	 * @param mode
	 */
	@Transactional
	public void setMode(final Device device, final GpioPin gpioPin, final OperationMode mode) {
		final DeviceMode deviceMode = getDeviceMode(device);

		deviceMode.getOperationModes().put(gpioPin, mode);
		deviceModeRepo.save(deviceMode);
	}

	/**
	 *
	 * @param device
	 *           device
	 * @return initialised mode
	 */
	public DeviceMode getDeviceMode(final Device device) {
		DeviceMode deviceMode = deviceModeRepo.findByDeviceId(device.getId());
		if (null == deviceMode) {
			deviceMode = new DeviceMode(device);
			deviceModeRepo.save(deviceMode);
		}
		return deviceMode;

	}

}
