package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.dao.UserDetailsDao;
import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.UserDetails;
import com.forsrc.jredmine.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsDao userDetailsDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails check(String username, String password) throws NoSuchObjectException, PasswordNotMatchException {
        UserDetails userDetails = userDetailsDao.findById(username).orElse(null);
        if (userDetails == null) {
            throw new NoSuchObjectException(username);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new PasswordNotMatchException(username);
        }
        return userDetails;
    }
}
