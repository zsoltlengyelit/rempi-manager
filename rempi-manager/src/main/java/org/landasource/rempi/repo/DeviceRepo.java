package org.landasource.rempi.repo;

import org.landasource.rempi.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepo extends CrudRepository<Device, Long> {

	Device findBySerial(String serial);

}
