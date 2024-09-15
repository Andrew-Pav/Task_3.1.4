package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.UserService;

import java.util.Set;

@Component
public class Init {

    public static Role roleUser = new Role(1L,"ROLE_USER");
    public static Role roleAdmin = new Role(2L,"ROLE_ADMIN");
    public static User user = new User(1L, "user", "user", 1, "user", "$2a$12$uC0Nc5SMq.Ga1jM9EC/n5O6Mjg/MGF8Jasw0w8OgaBCB8SijM0zf2", Set.of(roleUser));
    public static User admin = new User(2L, "admin", "admin", 1, "admin","$2a$12$WRW2HFGxW3v4DexCu.Nvd.kYGEj95uO9cUwghGqJTeh.VBvDEO4H6", Set.of(roleUser, roleAdmin));

    @Autowired
    public Init(UserService userService) {
        userService.saveUser(user);
        userService.saveUser(admin);
    }
}
