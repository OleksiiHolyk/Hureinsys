package service;

import dao.user.UserRepository;
import entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import util.EntityPage;
import util.FilterOptions;

/**
 * Created by Oleksii on 22.12.2015.
 */
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void createUser(User user) {
        userRepository.createUser(user);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User updateUser(User updatedUser) {
        return userRepository.findUserAndUpdate(updatedUser);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteUserById(id);
    }

    @Override
    public boolean userByIdExists(String id) {
        return userRepository.userByIdExists(id);
    }

    @Override
    public boolean userByEmailExists(String email) {
        return userRepository.userByEmailExists(email);
    }

    @Override
    public EntityPage<User> findAllUsers(FilterOptions filterOptions) {
        return userRepository.findAllUsers(filterOptions);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public  User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
