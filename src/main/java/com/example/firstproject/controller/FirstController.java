package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("username", "Jeong-Dong");
        return "greetings";
    }

    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("username", "Dong");
        return "goodbye";
    }
}
