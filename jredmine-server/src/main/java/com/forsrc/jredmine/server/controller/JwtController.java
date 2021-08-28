package com.forsrc.jredmine.server.controller;

import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    // curl -X GET http://localhost:8080/jredmine-server/jwt/user_info  --header "Authorization: Bearer `curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login -s -v 2>&1 | grep "Authorization:" | awk '{print $3}'`"
    @GetMapping(path = "/jwt/user_info", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Principal> user(Principal principal) {
        System.out.println("-> Principal: " + principal);
        return ResponseEntity.ok(principal);
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login
    @PostMapping(path = "/jwt/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        UserDetails userDetails = (UserDetails)auth.getPrincipal();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                Optional.ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(Collections.EMPTY_LIST)
        );

        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtTokenUtil.generateAccessToken(user))
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .body(userDetails);

    }
}