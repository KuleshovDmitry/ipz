package com.nixsolutions.ppp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/hello")

public class HelloController {

    @RequestMapping(method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "hello";
    }

}
