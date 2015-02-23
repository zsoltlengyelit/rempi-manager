package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Zsolti
 *
 */
public interface RoleRepo extends CrudRepository<Role, Long> {

	Role findByName(String name);

}
