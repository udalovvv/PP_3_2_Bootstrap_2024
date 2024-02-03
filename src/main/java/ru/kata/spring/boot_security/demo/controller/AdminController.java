package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/admin";

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService,
                           RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("")
    public String index(Model userModel,
                        Authentication authentication) {
        userModel.addAttribute("users", userService.showAll());
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute("authentication", authentication);
        userModel.addAttribute("authUser", (UserDetails) authentication.getPrincipal());
        return "pages/index";
    }

    @GetMapping("/add")
    public String add(Model userModel,
                         Authentication authentication) {
        userModel.addAttribute("newUser", new User());
        userModel.addAttribute("authentication", authentication);
        userModel.addAttribute("authUser", (UserDetails) authentication.getPrincipal());
        return "pages/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newUser") @Valid User newUser,
                      BindingResult bindingResult,
                      Authentication authentication,
                      Model userModel) {
        if (bindingResult.hasErrors()) {
            userModel.addAttribute("authentication", authentication);
            userModel.addAttribute("authUser", (UserDetails) authentication.getPrincipal());
            return "pages/add";
        }
        userService.save(newUser);
        return REDIRECT_ADMIN;
    }


    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       Model userModel,
                       Authentication authentication) {
        userModel.addAttribute("user", userService.showUser(id));
        userModel.addAttribute("authentication", authentication);
        userModel.addAttribute("authUser", (UserDetails) authentication.getPrincipal());
        return "pages/edit";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") long id,
                       @ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       Model userModel,
                       Authentication authentication) {
        if (bindingResult.hasErrors()) {
            userModel.addAttribute("authentication", authentication);
            userModel.addAttribute("authUser", (UserDetails) authentication.getPrincipal());
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7");
            System.out.println(bindingResult);
            return "pages/edit";
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");

        userService.update(id, user);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return REDIRECT_ADMIN;
    }



}
