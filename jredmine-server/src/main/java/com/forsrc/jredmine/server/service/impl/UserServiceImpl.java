package com.forsrc.jredmine.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.dao.UserDao;
import com.forsrc.jredmine.server.dao.mapper.UserMapper;
import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.service.UserService;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserMapper userMapper;

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
//            put = {
//                    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getPk()", condition = "#user != null", unless = "#result == null")
//            },
            evict = {
                    @CacheEvict(value = CACHE_PAGE_NAME),
                    @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '/' + #user.getPk()", condition = "#user != null"),
                    //@CacheEvict(value = CACHE_NAME, key = "UserDetailsServiceImpl.class.getName() + '/' + #t.getPk()")
    })
    public User save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    //@Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getPk()")
    @Caching(
//            put = {
//                    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #user.getPk()", condition = "#user != null" , unless = "#result == null")
//            },
            evict = {
                    @CacheEvict(value = CACHE_PAGE_NAME),
                    @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '/' + #user.getPk()", condition = "#user != null"),
                    //@CacheEvict(value = CACHE_NAME, key = "UserDetailsServiceImpl.class.getName() + '/' + #t.getPk()")
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

	@Override
	@CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #username")
	public void updateJwtToken(String username, String jwtToken) {
		userMapper.updateJwtToken(username, jwtToken);
	}
}
