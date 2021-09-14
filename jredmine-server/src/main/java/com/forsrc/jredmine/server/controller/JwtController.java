package com.forsrc.jredmine.server.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.LoginService;
import com.forsrc.jredmine.server.service.UserService;
import com.forsrc.jredmine.server.utils.JwtTokenUtil;

@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @Autowired
    private LoginService loginService;
    
    // curl -X GET http://localhost:8080/jredmine-server/jwt/user_info  --header "Authorization: Bearer `curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login -s -v 2>&1 | grep "Authorization:" | awk '{print $3}'`"
    @GetMapping(path = "/jwt/user_info", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Principal> user(Principal principal) {
        System.out.println("-> Principal: " + principal);
        return ResponseEntity.ok(principal);
    }

    // curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login
    @PostMapping(path = "/jwt/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetails> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws NoSuchObjectException, PasswordNotMatchException {

//        Authentication auth = authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//
//        
//        UserDetails userDetails = (UserDetails)auth.getPrincipal();
        
    	UserDetails userDetails = loginService.check(user.getUsername(), user.getPassword());


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                Optional.ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(Collections.EMPTY_LIST)
        );

        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        
        String jwtToken = JwtTokenUtil.generateAccessToken(user);

        // userService.updateJwtToken(user.getUsername(), jwtToken);
        user.setPassword(null);
        user.setJwtToken(jwtToken);
        userService.update(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = ((HttpServletRequest) request).getSession();
        String sessionId = session.getId();


        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .header("JREDMINE_SERVER_SESSION", sessionId)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "JREDMINE_SERVER_SESSION")
                .header(HttpHeaders.SET_COOKIE, "JREDMINE_SERVER_SESSION=" + sessionId + "; SameSite=None;  Httponly; Secure")
                .header(HttpHeaders.SET_COOKIE, "jsessionid=" + sessionId + "; SameSite=None;  Httponly; Secure")
                .body(userDetails);

    }

    @GetMapping(path = "/jwt/status")
    public ResponseEntity<String> status() {
    	return ResponseEntity.ok().body("{\"status\":\"OK\"}");
    }
}