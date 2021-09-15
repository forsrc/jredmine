package com.forsrc.jredmine.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.UserService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserService userService;
	

    @GetMapping()
    public String test() {
        System.out.println("-> test");
        return "test";
    }
    
    @GetMapping("/change_password")
    public User testChangePassword() {
    	User user = new User();
    	user.setUsername("test");
    	user.setPassword("forsrc");
    	user = userService.update(user);
        return user;
    }
}