package api.rest.user;

import entity.user.User;
import entity.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.UsersService;
import util.CreatedObjResponse;
import util.EntityPage;
import util.FilterOptions;

import java.util.HashSet;

/**
 * Created by Oleksii on 22.12.2015.
 */
@RestController
@RequestMapping("/api/rest/usersService")
public class UserRestController {
    @Autowired
    UsersService usersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/user/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        HashSet<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ROLE_USER);
        user.setUserRoles(userRoles);

        usersService.createUser(user);

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(user.getId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/read/id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserById(@PathVariable("id") String id) {
        User user = usersService.findUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/read/username/{username}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        User user = usersService.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/read/all",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<User>> findAllUsers(@RequestBody FilterOptions filterOptions) {
        EntityPage<User> users = usersService.findAllUsers(filterOptions);
        if (users.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = usersService.findUserByEmail(auth.getName());
            updatedUser.setId(user.getId());
            headers.setLocation(ucBuilder.path("/user/read/id/{id}").buildAndExpand(updatedUser.getId()).toUri());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usersService.updateUser(updatedUser), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/delete/id/{id}",
            method = RequestMethod.POST)
    public ResponseEntity<User> deleteUser(@PathVariable("id") String id) {
        if (!usersService.userByIdExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usersService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
