package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
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

    @GetMapping("/new")
    public String addNew(Model userModel) {
        userModel.addAttribute("newUser", new User());
        return "pages/new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("newUser")User newUser) {
        System.out.println(newUser.toString() + newUser.getRoles());
        userService.save(newUser);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping("/show")
//    public String show(@RequestParam("id") long id, Model userModel) {
//        userModel.addAttribute("user", userService.showUser(id));
//        return "pages/show";
//    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") long id, Model userModel) {
        userModel.addAttribute("user", userService.showUser(id));
        return "pages/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") long id ,@ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/updatemodal")
    public String updateWithModal(@ModelAttribute("user") User user) {
        userService.update(user.getId(), user);
        return "redirect:/admin";
    }



}
