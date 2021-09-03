package com.forsrc.jredmine.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.UserDetails;
import com.forsrc.jredmine.server.service.LoginService;
import com.forsrc.jredmine.server.service.UserDetailsService;

@Service
@Transactional(rollbackFor = {Exception.class})
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails check(String username, String password) throws NoSuchObjectException, PasswordNotMatchException {
        UserDetails userDetails = userDetailsService.getByUsername(username);
        if (userDetails == null) {
            throw new NoSuchObjectException(username);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new PasswordNotMatchException(username);
        }
        return userDetails;
    }
}
