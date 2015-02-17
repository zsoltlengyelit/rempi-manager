package org.landasource.rempi.manager.api;

import java.util.Map.Entry;
import java.util.Set;

import org.landasource.rempi.manager.api.http.BadRequestException;
import org.landasource.rempi.manager.core.clientstate.GpioState;
import org.landasource.rempi.manager.core.clientstate.StateStore;
import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinState;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/api/client")
public class ClientApiImpl {

	@Autowired
	private StateStore stateStore;

	@Autowired
	private DeviceRepo deviceRepo;

	@ResponseBody
	@RequestMapping(value = "/{clientId}/state", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ClientState getClientState(@PathVariable("clientId") final String serial) {

		final Device device = deviceRepo.findBySerial(serial);
		if (null == device) {
			throw new BadRequestException();
		}

		final ClientState clientState = new ClientState();
		final GpioState state = stateStore.getState(serial);
		final Set<Entry<GpioPin, PinState>> entrySet = state.entrySet();
		for (final Entry<GpioPin, PinState> entry : entrySet) {
			clientState.getGpio().put(entry.getKey(), entry.getValue());
		}

		return clientState;
	}
}
