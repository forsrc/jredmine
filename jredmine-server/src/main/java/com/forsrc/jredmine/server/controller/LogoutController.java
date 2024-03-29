package com.forsrc.jredmine.server.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogoutController {

    @RequestMapping(path = "/oauth/logout")
    // @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> user(HttpServletRequest request, HttpServletResponse response, Principal principal,
                                     String referer, @RequestParam("jredmine_referer") String jredmineReferer) {
        String user = principal == null ? "NO USER to logout" : principal.getName();
        new SecurityContextLogoutHandler().logout(request, null, null);
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        try {
            redirectStrategy.sendRedirect(request, response, jredmineReferer != null ? jredmineReferer : request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-> logout: " + principal);
        return ResponseEntity.ok().header("logout_user", user).build();
    }

}