package org.landasource.rempi.repo;

import org.landasource.rempi.model.DeviceType;
import org.springframework.data.repository.CrudRepository;

public interface DeviceTypeRepo extends CrudRepository<DeviceType, Long> {

	DeviceType findByName(String name);

}
