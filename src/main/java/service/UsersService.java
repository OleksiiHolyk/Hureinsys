package service;

import entity.user.User;
import util.EntityPage;
import util.FilterOptions;

/**
 * Created by Oleksii on 22.12.2015.
 */
public interface UsersService {

    void createUser(User user);

    User findUserById(String id);

    User updateUser(User updatedUser);

    void deleteUserById(String id);

    boolean userByIdExists(String id);

    boolean userByEmailExists(String email);

    EntityPage<User> findAllUsers(FilterOptions filterOptions);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
