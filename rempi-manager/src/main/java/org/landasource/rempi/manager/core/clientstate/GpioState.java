package org.landasource.rempi.manager.core.clientstate;

import java.util.HashMap;

import org.landasource.rempi.common.GpioPin;
import org.landasource.rempi.common.PinMode;
import org.landasource.rempi.common.PinState;

public class GpioState extends HashMap<GpioPin, PinState> {

	@Override
	public PinState get(final Object key) {

		GpioPin pinKey = null;

		if (key instanceof String) {
			final Integer index = Integer.valueOf((String) key);
			pinKey = GpioPin.byIndex(index);

		} else if (key instanceof Number) {
			pinKey = GpioPin.byIndex(((Number) key).intValue());
		} else {
			pinKey = (GpioPin) key;
		}

		if (!containsKey(pinKey)) {
			put(pinKey, new PinState(PinMode.OUT, false));// default value
		}

		return super.get(pinKey);
	}

	public PinState getByGpio(final Number num) {
		return get(GpioPin.byIndex(num.intValue()));
	}

}
