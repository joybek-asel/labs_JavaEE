package com.example.lab5_asel.controller;


import com.example.lab5_asel.model.Users;
import com.example.lab5_asel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomePageController {

    private final UserService usersService;

    //CONSTRUCTOR
    @Autowired
    public HomePageController(UserService usersService) {
        this.usersService = usersService;
    }



    //MAIN PAGE
    @GetMapping("/main-page")
    public String mainPage(Principal principal, Model model) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        if (user.getRole().equals("ROLE_ADMIN")) {
            return "homePage";
        } else {
            return "homePage-user";
        }
    }
}
















//import com.example.lab5_asel.model.Users;
//import com.example.lab5_asel.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.security.Principal;
//
//@Controller
//public class HomePageController {
//
//    private final UserService usersService;
//
//    @Autowired
//    public HomePageController(UserService usersService) {
//        this.usersService = usersService;
//    }
//
//    @GetMapping("/main-page")
//    public String mainPage(Principal principal, Model model) {
//        String username = principal.getName();
//        Users user = usersService.findByUsername(username);
//        model.addAttribute("user", user);
//        return "homePage";
//    }
//}
