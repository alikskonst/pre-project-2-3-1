package edu.kata.task231.controller;

import edu.kata.task231.model.User;
import edu.kata.task231.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserManageControllerImpl {

    @Autowired
    private UserService userService;

    // переход
    @GetMapping("/create")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "user_manage";
    }

    // переход
    @GetMapping("/update/{id}")
    public String preUpdate(ModelMap modelMap, Long id) {
        modelMap.addAttribute("user", userService.findOne(id));
        return "user_manage";
    }

    // сохранение
    @PostMapping("/create")
    public String create(User user) {
        userService.save(user);
        return "redirect:/index";
    }

    // обновление
    @PostMapping("/update")
    public String update(User user) {
        userService.save(user);
        return "redirect:/index";
    }

    // удаление
    @DeleteMapping("/remove/{id}")
    public String remove(Long id) {
        userService.remove(id);
        return "redirect:/index";
    }
}
