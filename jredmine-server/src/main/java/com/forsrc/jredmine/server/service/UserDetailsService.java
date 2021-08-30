package com.forsrc.jredmine.server.service;

import com.forsrc.jredmine.server.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface UserDetailsService extends BaseService<UserDetails, String> {

    @Transactional(readOnly = true)
    public UserDetails getByUsername(String username);

}
