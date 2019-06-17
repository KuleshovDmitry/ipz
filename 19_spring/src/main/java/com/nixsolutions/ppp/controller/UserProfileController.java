package com.nixsolutions.ppp.controller;

import com.nixsolutions.ppp.model.entity.User;
import com.nixsolutions.ppp.service.RentedBookService;
import com.nixsolutions.ppp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private RentedBookService rentedBookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserProfile(Model model, @RequestParam(value = "userId") String userIdString){
        long id =Long.parseLong(userIdString);
        User user;
        if (id != 0) {
            user = userService.getUser(id);
        }
        else{
            user=userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        }
        model.addAttribute("user", user);
        model.addAttribute("rentedBookList", rentedBookService.selectByUser(user));
        return "userProfile";//TODO is low security
    }
}
