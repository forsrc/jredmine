package com.forsrc.jredmine.server.service;

import com.forsrc.jredmine.server.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface UserService extends BaseService<User, String> {

    @Transactional(readOnly = true)
    public User getByUsername(String username);

}
