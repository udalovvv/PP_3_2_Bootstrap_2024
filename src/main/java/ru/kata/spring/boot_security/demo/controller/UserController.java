package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.id.equals(#id)")
    @GetMapping("")
    public String show(@RequestParam("id") long id,
                       Model userModel,
                       Authentication authentication) {
        userModel.addAttribute("user", userService.findById(id));
        userModel.addAttribute("authentication", authentication);
        userModel.addAttribute("authUser",  authentication.getPrincipal());
        return "pages/show";
    }
}
