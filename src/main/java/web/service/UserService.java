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

    void updateUserStep1(long id, String name, String surname, int age,
                    String username, String password, List<String> roles);

    void updateUserStep2(User user, List<String> roles);
}
