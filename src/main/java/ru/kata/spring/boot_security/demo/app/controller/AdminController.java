package ru.kata.spring.boot_security.demo.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.app.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.app.model.User;
import ru.kata.spring.boot_security.demo.app.service.RoleService;
import ru.kata.spring.boot_security.demo.app.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("currentUser", userService.getUser());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.findAll());
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesFrom", required = false) List<String> roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }


    @PostMapping("/editUser")
    public String editUser(@ModelAttribute("user") User user, @RequestParam(value = "rolesFromEdit", required = false) List<String> roles) {
        userService.updateUser(user, roles);
        return "redirect:/admin";
    }


    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}