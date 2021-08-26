package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User check(String username, String password) throws NoSuchObjectException, PasswordNotMatchException {
        User user = userDao.findById(username).orElse(null);
        if (user == null) {
            throw new NoSuchObjectException(username);
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotMatchException(username);
        }
        return user;
    }
}
