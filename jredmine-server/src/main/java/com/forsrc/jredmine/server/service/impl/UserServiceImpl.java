package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.dao.AuthorityDao;
import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.AuthorityService;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #username")
    public User getByUsername(String username) {
        return userDao.getOne(username);
    }

    @Override

    @Caching(
            put = {
                    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getKey()")
            },
            evict = {
                    @CacheEvict(value = CACHE_PAGE_NAME),
                    @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '/' + #t.getKey()"),
                    //@CacheEvict(value = CACHE_NAME, key = "UserDetailsServiceImpl.class.getName() + '/' + #t.getKey()")
    })
    public User save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    //@Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getKey()")
    @Caching(
            put = {
                    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getKey()")
            },
            evict = {
                    @CacheEvict(value = CACHE_PAGE_NAME),
                    @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '/' + #t.getKey()"),
                    //@CacheEvict(value = CACHE_NAME, key = "UserDetailsServiceImpl.class.getName() + '/' + #t.getKey()")
            })
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
