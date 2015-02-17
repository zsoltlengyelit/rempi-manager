package org.landasource.rempi.manager.core.clientstate;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinMode;
import org.landasource.rempi.manager.core.gpio.PinState;
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

	private final ConcurrentMap<String, GpioState> states = new ConcurrentHashMap<String, GpioState>();

	public GpioState getState(final String serial) {
		initClientState(serial);
		return states.get(serial);
	}

	/**
	 *
	 * @param serial
	 * @param pin
	 * @param value
	 */
	public void set(final String serial, final GpioPin pin, final boolean value) {

		initClientState(serial);
		final GpioState gpioState = states.get(serial);
		gpioState.put(pin, new PinState(PinMode.OUT, value));
	}

	public boolean isEnabled(final String serial, final GpioPin pin) {
		initClientState(serial);
		final PinState pinState = states.get(serial).get(pin);
		return null != pinState && pinState.isEnabled();

	}

	private void initClientState(final String serial) {
		if (!states.containsKey(serial)) {
			states.put(serial, new GpioState());
		}
	}

}
