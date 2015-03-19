package org.landasource.rempi.manager.core.notifications.device;

import java.sql.Date;
import java.util.Calendar;

import org.landasource.rempi.manager.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeviceCheckinNotifier {

	@Autowired
	private SimpMessagingTemplate template;

	public void deviceCheckedIn(final Device device) {

		final DeviceCheckinDTO deviceCheckinDTO = new DeviceCheckinDTO();
		deviceCheckinDTO.setDeviceId(device.getId());
		deviceCheckinDTO.setDeviceName(device.getName());
		deviceCheckinDTO.setTime(Calendar.getInstance().getTimeInMillis());
		deviceCheckinDTO.setStatus("OK");

		template.convertAndSend("/devices/checkin", deviceCheckinDTO);
	}

	/**
	 *
	 * @param device
	 */
	public void deviceLost(final Device device, final Date lastCheckin) {

		final DeviceCheckinDTO deviceCheckinDTO = new DeviceCheckinDTO();
		deviceCheckinDTO.setDeviceId(device.getId());
		deviceCheckinDTO.setDeviceName(device.getName());
		deviceCheckinDTO.setTime(lastCheckin.getTime());
		deviceCheckinDTO.setStatus("LOST");

		template.convertAndSend("/devices/checkin", deviceCheckinDTO);
	}

}
