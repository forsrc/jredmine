package com.forsrc.jredmine.server.controller;

import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.LoginService;
import com.forsrc.jredmine.server.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

@Controller
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String toLoginPage(HttpServletResponse response) {
        return "/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserDetails userDetails = null;
        try {
            userDetails = loginService.check(user.getUsername(), user.getPassword());
        } catch (NoSuchObjectException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "/login";
        } catch (PasswordNotMatchException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "/login";
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                Optional.ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(Collections.EMPTY_LIST)
        );
        String jwtToken = JwtTokenUtil.generateAccessToken(user);

        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
        HttpSession session = ((HttpServletRequest) request).getSession();
        String sessionId = session.getId();
        response.addHeader(HttpHeaders.SET_COOKIE, "JREDMINE_SERVER_SESSION=" + sessionId + "; SameSite=None;  Httponly; Secure");
        response.addHeader(HttpHeaders.SET_COOKIE, "jsessionid=" + sessionId + "; SameSite=None;  Httponly; Secure");
        return "/home";
    }
}