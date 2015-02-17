package org.landasource.rempi.manager.repo;

import org.landasource.rempi.manager.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Zsolti
 *
 */
public interface UserRepo extends CrudRepository<User, Long> {

	User findByUsername(String name);

}
