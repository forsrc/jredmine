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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#username")
    public User getByUsername(String username) {
        return userDao.getOne(username);
    }

    @Override
    public BaseDao<User, String> getBaseDao() {
        return userDao;
    }
}
