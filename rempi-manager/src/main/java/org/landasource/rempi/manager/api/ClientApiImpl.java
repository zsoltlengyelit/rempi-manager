package org.landasource.rempi.manager.api;

import java.util.Map.Entry;
import java.util.Set;

import org.landasource.rempi.manager.core.clientstate.GpioState;
import org.landasource.rempi.manager.core.clientstate.StateStore;
import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientApiImpl implements ClientApi {

	@Autowired
	private StateStore stateStore;

	@Override
	public ClientState getClientState(final String serial) {

		final ClientState clientState = new ClientState();
		final GpioState state = stateStore.getState(serial);
		final Set<Entry<GpioPin, PinState>> entrySet = state.entrySet();
		for (final Entry<GpioPin, PinState> entry : entrySet) {
			clientState.getGpio().put(entry.getKey(), entry.getValue());
		}

		return clientState;
	}
}
