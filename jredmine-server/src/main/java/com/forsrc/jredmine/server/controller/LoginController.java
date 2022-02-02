package com.forsrc.jredmine.server.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.LoginService;
import com.forsrc.jredmine.server.utils.JwtTokenUtil;

@Controller
public class LoginController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String toLoginPage(HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public String login(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserDetails userDetails = null;
        try {
            userDetails = loginService.check(user.getUsername(), user.getPassword());
        } catch (NoSuchObjectException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        } catch (PasswordNotMatchException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", e.getMessage());
            return "login";
        }

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails.getAuthorities()
        );
        String jwtToken = JwtTokenUtil.generateAccessToken(user);

        authentication
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwtToken);
        HttpSession session = ((HttpServletRequest) request).getSession();
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        String sessionId = session.getId();
        response.addHeader(HttpHeaders.SET_COOKIE, "JREDMINE_SERVER_SESSION=" + sessionId + "; SameSite=None;  Httponly; Secure");
        
        Cookie cookie = new Cookie("JREDMINE_SERVER_SESSION", sessionId);
        response.addCookie(cookie);
        //response.addHeader(HttpHeaders.SET_COOKIE, "jsessionid=" + sessionId + "; SameSite=None;  Httponly; Secure");
        return "redirect:/home;JREDMINE_SERVER_SESSION=" + sessionId;
    }
}