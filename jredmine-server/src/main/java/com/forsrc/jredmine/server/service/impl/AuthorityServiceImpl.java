package com.forsrc.jredmine.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.dao.AuthorityDao;
import com.forsrc.jredmine.server.dao.BaseDao;
import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.model.AuthorityPk;
import com.forsrc.jredmine.server.service.AuthorityService;

@Service
@Transactional(rollbackFor = { Exception.class })
public class AuthorityServiceImpl extends BaseServiceImpl<Authority, AuthorityPk> implements AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' +#username")
    public List<Authority> getByUsername(String username) {
        Authority entity = new Authority();
        entity.setUsername(username);
        Example<Authority> example = Example.of(entity);
        return authorityDao.findAll(example);
    }

    @Override
    @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' +#username")
    public void delete(String username) {
        List<Authority> list = getByUsername(username);
        for (Authority authority : list) {
            authorityDao.delete(authority);
        }
        removePageCache();
    }

    @Override
    /**
     * update Authorities<br/>
     * * all the username must be the same
     * 
     * @param list
     * @returnb
     */
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #list.get(0).username")
    public List<Authority> update(List<Authority> list) {
        return authorityDao.saveAll(list);
    }

    @Override
    /**
     * save Authorities<br/>
     * * all the username must be the same
     * 
     * @param list
     * @return
     */
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #list.get(0).username")
    public List<Authority> save(List<Authority> list) {
        return authorityDao.saveAll(list);
    }

    @Override
    public BaseDao<Authority, AuthorityPk> getBaseDao() {
        return authorityDao;
    }

    @Override
    public String[] removeKeys() {
        return new String[] { getClass().getName(), UserServiceImpl.class.getName() };
    }
}
