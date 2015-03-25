package org.landasource.rempi.manager.api;

import java.util.Calendar;
import java.util.Map.Entry;
import java.util.Set;

import net.wimpi.telnetd.io.terminal.ansi;

import org.landasource.rempi.manager.api.http.BadRequestException;
import org.landasource.rempi.manager.core.clientstate.GpioState;
import org.landasource.rempi.manager.core.clientstate.StateStore;
import org.landasource.rempi.manager.core.gpio.GpioPin;
import org.landasource.rempi.manager.core.gpio.PinState;
import org.landasource.rempi.manager.core.notifications.device.DeviceCheckinNotifier;
import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceMetadata;
import org.landasource.rempi.manager.repo.DeviceMetadataRepo;
import org.landasource.rempi.manager.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;

@Component
@RequestMapping("/api/client")
public class ClientApiImpl {

	@Autowired
	private StateStore stateStore;

	@Autowired
	private DeviceRepo deviceRepo;

	@Autowired
	private DeviceCheckinNotifier deviceCheckinNotifier;

	@Autowired
	private DeviceMetadataRepo deviceMetaDataRepo;

	@ResponseBody
	@RequestMapping(value = "/{clientId}/state", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ClientState getClientState(@PathVariable("clientId") final String serial) {

		final Device device = deviceRepo.findBySerial(serial);
		if (null == device) {
			throw new BadRequestException();
		}

		final ClientState clientState = new ClientState();
		final GpioState state = stateStore.getClientState(device);
		final Set<Entry<GpioPin, PinState>> entrySet = state.entrySet();
		for (final Entry<GpioPin, PinState> entry : entrySet) {
			clientState.getGpio().put(entry.getKey(), entry.getValue());
		}

		final DeviceMetadata metadata = deviceMetaDataRepo.findByDeviceId(device.getId());
		metadata.setLastCheckin(Calendar.getInstance().getTime());
		deviceMetaDataRepo.save(metadata);

		deviceCheckinNotifier.deviceCheckedIn(device);

		return clientState;
	}

	@ResponseBody
	@RequestMapping(value = "/{clientId}/hexstate", method = RequestMethod.GET, produces = org.springframework.http.MediaType.TEXT_PLAIN_VALUE)
	public String getClientStateHex(@PathVariable("clientId") final String serial) {
		final ClientState clientState = getClientState(serial);

		// model B+ has 40 pin
		final StringBuilder hexState = new StringBuilder(Strings.repeat("0", 40));

		for (final Entry<GpioPin, PinState> state : clientState.getGpio().entrySet()) {
			final GpioPin key = state.getKey();
			final boolean enabled = state.getValue().isEnabled();

			hexState.setCharAt(key.getPinNumber(), enabled ? '1' : '0');
		}

		final int binState = Integer.parseInt(hexState.toString(), 2);
		return Integer.toHexString(binState);
	}
}
