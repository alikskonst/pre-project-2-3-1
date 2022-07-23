package edu.kata.task231.controller;

import edu.kata.task231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("userList", userService.findAll());
        return "index";
    }
}
