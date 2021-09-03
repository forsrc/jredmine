package com.forsrc.jredmine.server.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.model.User;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface UserService extends BaseService<User, String> {

    @Transactional(readOnly = true)
    public User getByUsername(String username);

    void updateJwtToken(String username, String jwtToken);
}
