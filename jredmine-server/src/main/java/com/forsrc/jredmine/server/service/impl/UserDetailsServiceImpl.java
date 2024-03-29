package com.forsrc.jredmine.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.dao.UserDetailsDao;
import com.forsrc.jredmine.server.model.UserDetails;
import com.forsrc.jredmine.server.service.UserDetailsService;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetails, String> implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #username")
    public UserDetails getByUsername(String username) {
        return userDetailsDao.getOne(username);
    }

    @Override
    public BaseDao<UserDetails, String> getBaseDao() {
        return userDetailsDao;
    }
}
