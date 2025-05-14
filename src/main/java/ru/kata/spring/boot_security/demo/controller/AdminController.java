package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "showAllUsers";
    }

    @GetMapping("/add_user")
    public String showAddUserForm() {
        return "addUser";
    }



    @PostMapping("/save_user")
    public String saveUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );

        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit_user")
    public String showEditUserForm(@RequestParam("id") int id, ModelMap model) {
        User user = userService.getUserById(id);
        List<Role> allRoles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "editUser";
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestBody User user) {
        user.setRoles(
                user.getRoles().stream()
                        .map(role -> roleService.findByName(role.getName()))
                        .collect(Collectors.toSet())
        );
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
