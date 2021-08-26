package com.forsrc.jredmine.server.service.impl;

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
public class UserServiceImpl implements UserService {


    private static final String CACHE_NAME = "spring/cache/jredmine/User";

    @Autowired
    private UserDao userDao;


    @Override
    @CachePut(value = CACHE_NAME, key = "#user.username")
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages/"),
            @CacheEvict(value = CACHE_NAME, key = "#user.username")
    })
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "#user.username")
    @CacheEvict(value = CACHE_NAME)
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages/"),
            @CacheEvict(value = CACHE_NAME, key = "#user.username")
    })
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#username")
    public User getByUsername(String username) {
        return userDao.getOne(username);
    }

    @Override
    @Cacheable(value = CACHE_NAME + "/pages", key = "#page + '-' + #size")
    public Page<User> get(int page, int size) {
        Page<User> p = userDao.findAll(PageRequest.of(page, size));
        return p;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages"),
            @CacheEvict(value = CACHE_NAME, key = "#username")
    })
    public void delete(String username) {
        userDao.deleteById(username);
    }

}
