package com.fankai.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/say")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "卢莹大傻逼";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("loginName", "admin");
        model.addAttribute("loginId", "27");
        return "success";
    }
}
