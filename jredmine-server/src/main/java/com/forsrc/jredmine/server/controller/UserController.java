package com.forsrc.jredmine.server.controller;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_info")
public class UserController {


    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Principal> user(Principal principal) {
        // System.out.println("-> Principal: " + principal);
        return ResponseEntity.ok(principal);
    }


}