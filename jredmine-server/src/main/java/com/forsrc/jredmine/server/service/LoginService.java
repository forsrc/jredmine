package com.forsrc.jredmine.server.service;


import com.forsrc.jredmine.server.exception.NoSuchObjectException;
import com.forsrc.jredmine.server.exception.PasswordNotMatchException;
import com.forsrc.jredmine.server.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { Exception.class })
public interface LoginService {
    @Transactional(readOnly = true)
    User check(String username, String password) throws NoSuchObjectException, PasswordNotMatchException;
}
