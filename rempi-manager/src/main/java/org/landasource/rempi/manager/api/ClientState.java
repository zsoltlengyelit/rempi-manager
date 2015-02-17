package org.landasource.rempi.manager.api;

import java.util.HashMap;
import java.util.Map;

import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinState;

/**
 * General status of client received from manager.
 *
 * @author Zsolti
 *
 */
public class ClientState {

	private Map<GpioPin, PinState> gpio;

	public Map<GpioPin, PinState> getGpio() {
		if (null == gpio) {
			gpio = new HashMap<GpioPin, PinState>();
		}

		return gpio;
	}

	@Override
	public String toString() {
		return "ClientState [gpio=" + getGpio() + "]";
	}

}
