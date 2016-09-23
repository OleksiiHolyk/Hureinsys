
package ua.com.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ua.com.model.User;

import java.util.List;

/*CRUD
* Create - POST
* Read - GET
* Update - PATH
* Delete - DELETE*/

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
}
