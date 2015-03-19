package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.DeviceMetadata;
import org.springframework.data.repository.CrudRepository;

public interface DeviceMetadataRepo extends CrudRepository<DeviceMetadata, Long> {

	/**
	 *
	 * @param deviceId
	 *           ID of device
	 * @return its device
	 */
	DeviceMetadata findByDeviceId(Long deviceId);

}
