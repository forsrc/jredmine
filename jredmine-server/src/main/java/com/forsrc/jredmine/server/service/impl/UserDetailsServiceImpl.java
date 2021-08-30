package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.dao.UserDetailsDao;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.model.UserDetails;
import com.forsrc.jredmine.server.service.UserDetailsService;
import com.forsrc.jredmine.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetails, String> implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#username")
    public UserDetails getByUsername(String username) {
        return userDetailsDao.getOne(username);
    }

    @Override
    public BaseDao<UserDetails, String> getBaseDao() {
        return userDetailsDao;
    }
}
