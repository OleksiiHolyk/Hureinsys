package ua.com.hureinsys.service;

import ua.com.hureinsys.entity.user.User;
import ua.com.hureinsys.util.EntityPage;
import ua.com.hureinsys.util.FilterOptions;

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