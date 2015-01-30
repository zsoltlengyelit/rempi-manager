package org.landasource.rempi.repo;

import org.landasource.rempi.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Zsolti
 *
 */
public interface UserRepo extends CrudRepository<User, Long> {

	User findByUsername(String name);

}
