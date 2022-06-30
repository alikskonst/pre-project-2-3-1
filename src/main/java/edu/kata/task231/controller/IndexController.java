package edu.kata.task231.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(ModelMap modelMap) {
        List<String> messages = new ArrayList<>();
        messages.add("Index page");
        modelMap.addAttribute("messages", messages);
        return "index";
    }
}
