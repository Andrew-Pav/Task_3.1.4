package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.models.User;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("allUsers", userService.getAllUsers());

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userInfo", user);
        model.addAttribute("currentUser", user);
        model.addAttribute("roles", userService.getAllRoles());
        return "users";
    }

    @GetMapping("/")
    public String indexPage1() {
        return "index";
    }

    @GetMapping("/index")
    public String indexPage2() {
        return "index";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, ModelMap model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("userInfo", user);

        return "user";
    }

    @PostMapping("/admin")
    public String saveUser(@ModelAttribute("currentUser") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}