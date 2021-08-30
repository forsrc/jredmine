package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass + '-' + #username")
    public User getByUsername(String username) {
        return userDao.getOne(username);
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass + '-' + #user.getKey()")
    public User save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass + '-' + #user.getKey()")
    public User update(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.update(user);
    }

    @Override
    public BaseDao<User, String> getBaseDao() {
        return userDao;
    }
}
