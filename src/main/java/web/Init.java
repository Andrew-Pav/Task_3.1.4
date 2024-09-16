package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.UserService;

import java.util.Set;

@Component
public class Init {

    public final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Role roleUser = new Role(1L,"ROLE_USER");
    public static Role roleAdmin = new Role(2L,"ROLE_ADMIN");
    public static User user = new User(1L, "user", "user", 1, "user", passwordEncoder.encode("user"), Set.of(roleUser));
    public static User admin = new User(2L, "admin", "admin", 1, "admin", passwordEncoder.encode("admin"), Set.of(roleUser, roleAdmin));

    @Autowired
    public Init(UserService userService) {
        userService.saveUser(user);
        userService.saveUser(admin);
    }
}
