package com.forsrc.boot.client.web.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/login")
    public LoginVo login(@RequestParam("username") String username, @RequestParam("password") char[] password) {
        LoginVo loginVo = new LoginVo();
        loginVo.setUsername(username);
        return loginVo;
    }

    public static class LoginVo {

        private int state;
        private long userId;
        private String username;
        private String message;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
