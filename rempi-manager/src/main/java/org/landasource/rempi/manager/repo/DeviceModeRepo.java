package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.Device;
import org.landasource.rempi.manager.model.DeviceMode;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Zsolti
 *
 */
public interface DeviceModeRepo extends CrudRepository<DeviceMode, Long> {

	/**
	 *
	 * @param deviceId
	 *           id of {@link Device}
	 * @return mode
	 */
	DeviceMode findByDeviceId(Long deviceId);

}
