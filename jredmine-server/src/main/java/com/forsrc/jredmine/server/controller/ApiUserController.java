package com.forsrc.jredmine.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.BaseService;
import com.forsrc.jredmine.server.service.UserService;

@RestController
@RequestMapping("/api/user")
public class ApiUserController extends BaseController<User, String> {

    @Autowired
    private UserService userService;

    @Override
    public BaseService<User, String> getBaseService() {
        return userService;
    }
}