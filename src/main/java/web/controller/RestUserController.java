package web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestUserController {

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/users", params = "id")
    public ResponseEntity<User> getOneUser(@RequestParam long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addUser(@RequestBody Map<String, Object> body) {
        User newUser = parseUser(body.get("user"));
        List<String> roles = parseRoles(body.get("roles"));

        userService.updateUserStep2(newUser, roles);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping(value = "/users", params = "id")
    public ResponseEntity<HttpStatus> editUser(@RequestBody Map<String, Object> body, @RequestParam long id) {
        User updateUser = parseUser(body.get("user"));
        List<String> roles = parseRoles(body.get("roles"));

        userService.updateUserStep1(id, updateUser.getName(), updateUser.getSurname(),
                updateUser.getAge(), updateUser.getUsername(), updateUser.getPassword(), roles);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/users")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody long id) {
        userService.deleteUser(id);
        System.out.println(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private User parseUser(Object json) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(json), User.class);
    }

    private List<String> parseRoles(Object json) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(json), new TypeToken<List<String>>() {}.getType());
    }

}
