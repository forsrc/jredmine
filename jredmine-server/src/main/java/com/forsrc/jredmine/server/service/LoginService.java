package com.forsrc.jredmine.server.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.UserDetails;

@Service
@Transactional(rollbackFor = { Exception.class })
public interface LoginService {
    @Transactional(readOnly = true)
    UserDetails check(String username, String password) throws NoSuchObjectException, PasswordNotMatchException;
}
