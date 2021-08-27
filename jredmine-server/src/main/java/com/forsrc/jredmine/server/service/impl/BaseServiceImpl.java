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


    public static final String CACHE_NAME = "spring/cache/jredmine";

    @Override
    @CachePut(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getKey()")
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages/"),
            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getKey()")
    })
    public T save(T t) {
        return getBaseDao().save(t);
    }

    @Override
    @CachePut(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getKey()")
    @CacheEvict(value = CACHE_NAME)
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages"),
            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getKey()")
    })
    public T update(T t) {
        return getBaseDao().save(t);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME + "/pages", key = "#root.targetClass + '-' + #pk")
    public T get(PK pk) {
        return getBaseDao().findById(pk).get();
    }

    @Override
    @Cacheable(value = CACHE_NAME + "/pages", key = "#root.targetClass + '-' + #page + '-' + #size")
    public Page<T> get(int page, int size) {
        Page<T> p = getBaseDao().findAll(PageRequest.of(page, size));
        return p;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_NAME + "/pages", key = "#root.targetClass + '-' + #pk")
    })
    public void delete(PK pk) {
        getBaseDao().deleteById(pk);
    }

}
