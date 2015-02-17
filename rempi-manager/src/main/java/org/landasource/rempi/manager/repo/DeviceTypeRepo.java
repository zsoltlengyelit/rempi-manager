package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.DeviceType;
import org.springframework.data.repository.CrudRepository;

public interface DeviceTypeRepo extends CrudRepository<DeviceType, Long> {

	DeviceType findByName(String name);

}
