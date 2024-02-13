package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private static final String AUTHUSER = "authUser";
    private static final String AUTHENTICATION = "authentication";

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index(Model userModel,
                        Authentication authentication) {
        userModel.addAttribute("users", userService.findAll());
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        userModel.addAttribute("rolesList", roleService.findAllRole());
        userModel.addAttribute("admin", userService.findByEmail(authentication.getName()));
        return "adminpanel/admin";
    }

    @GetMapping("/add")
    public String add(Model userModel,
                         Authentication authentication) {
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        return "pages/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return REDIRECT_ADMIN;
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       Model userModel,
                       Authentication authentication) {
        userModel.addAttribute("user", userService.findById(id));
        userModel.addAttribute(AUTHENTICATION, authentication);
        userModel.addAttribute(AUTHUSER, authentication.getPrincipal());
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @ModelAttribute("user") User user){
        userService.updateUser(id, user);
        return REDIRECT_ADMIN;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.deleteById(id);
        return REDIRECT_ADMIN;
    }
}
