package com.forsrc.jredmine.server.service.impl;

import com.forsrc.jredmine.server.service.BaseService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional(rollbackFor = {Exception.class})
public abstract class BaseServiceImpl<T extends Serializable, PK> implements BaseService<T, PK> {

    public static final String CACHE_NAME = "jredmine";
    public static final String CACHE_PAGE_NAME = CACHE_NAME + "/page";

    @Override
    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getKey()")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    @CacheEvict(value = CACHE_PAGE_NAME)
//    @Caching(evict = {
//            @CacheEvict(value = CACHE_PAGE_NAME),
//            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getKey()")
//    })
    public T save(T t) {
        return getBaseDao().save(t);
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getKey()")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    @CacheEvict(value = CACHE_PAGE_NAME)
    public T update(T t) {
        return getBaseDao().save(t);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #pk")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    public T get(PK pk) {
        return getBaseDao().findById(pk).get();
    }

    @Override
    @Cacheable(value = CACHE_PAGE_NAME, key = "#root.targetClass.getName() + '/' + #page + '-' + #size")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    public Page<T> page(int page, int size) {
        Page<T> p = getBaseDao().findAll(PageRequest.of(page, size));
        return p;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_PAGE_NAME),
            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #pk")
    })
//    @Caching(evict = {
//            @CacheEvict(value = CACHE_PAGE_NAME), @CacheEvict(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
//    })
    public void delete(PK pk) {
        getBaseDao().deleteById(pk);
    }

}
