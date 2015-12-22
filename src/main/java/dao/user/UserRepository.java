package dao.user;

import entity.user.User;
import util.EntityPage;
import util.FilterOptions;

/**
 * Created by Oleksii on 22.12.2015.
 */
public interface UserRepository {

    void createUser(User user);

    User findUserById(String id);

    User findUserAndUpdate(User user);

/*    void updateUser(User user);*/

    int deleteUserById(String id);

    boolean userByIdExists(String id);

    boolean userByEmailExists(String email);

    EntityPage<User> findAllUsers(FilterOptions filterOptions);

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
