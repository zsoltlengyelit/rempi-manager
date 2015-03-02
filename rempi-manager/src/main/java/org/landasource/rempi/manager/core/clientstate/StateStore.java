package org.landasource.rempi.manager.core.clientstate;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinMode;
import org.landasource.rempi.manager.core.gpio.PinState;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceMode;
import org.landasource.rempi.manager.model.OperationMode;
import org.landasource.rempi.manager.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Zsolti
 *
 */
@Component("stateStore")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StateStore implements Serializable {

	@Autowired
	private DeviceManager deviceManager;

	@Autowired
	private DeviceRepo deviceRepo;

	public GpioState getClientState(final Device device) {

		final DeviceMode deviceMode = deviceManager.getDeviceMode(device);

		final GpioState gpioState = new GpioState();
		final Map<GpioPin, OperationMode> operationModes = deviceMode.getOperationModes();

		for (final Entry<GpioPin, OperationMode> modeEntry : operationModes.entrySet()) {

			final GpioPin gpioPin = modeEntry.getKey();
			final OperationMode operationMode = modeEntry.getValue();

			// TODO implement auto mode here
			final boolean enabled = OperationMode.ON.equals(operationMode);
			gpioState.put(gpioPin, new PinState(PinMode.OUT, enabled));
		}

		return gpioState;
	}

}
