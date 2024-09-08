package web.service;

import web.models.Role;
import web.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User findByUsername(String username);

    void saveUser(User user);

    void deleteUser(Long id);

    User findUserById(Long id);

    List<Role> getAllRoles();
}
