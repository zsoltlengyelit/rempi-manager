package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepo extends CrudRepository<Device, Long> {

	Device findBySerial(String serial);

}
