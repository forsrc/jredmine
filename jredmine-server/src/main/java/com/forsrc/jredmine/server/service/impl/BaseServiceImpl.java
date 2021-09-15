package com.forsrc.jredmine.server.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.model.BaseModel;
import com.forsrc.jredmine.server.service.BaseService;
import com.forsrc.jredmine.server.utils.BeanUtil;

@Service
@Transactional(rollbackFor = {Exception.class})
public abstract class BaseServiceImpl<T extends BaseModel<PK>, PK> implements BaseService<T, PK> {

    public static final String CACHE_NAME = "jredmine";
    public static final String CACHE_PAGE_NAME = CACHE_NAME + "/page";

    @Override
    //@CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()", condition = "#t != null" , unless = "#result == null")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    @CacheEvict(value = CACHE_PAGE_NAME)
//    @Caching(evict = {
//            @CacheEvict(value = CACHE_PAGE_NAME),
//            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass + '-' + #t.getPk()")
//    })
    public T save(T t) {
        return getBaseDao().save(t);
    }

    @Override
    //@CachePut(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()", condition = "#t != null" , unless = "#result == null")
    //@CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    @CacheEvict(value = CACHE_PAGE_NAME)
    public T update(T t) {
    	T source = getBaseDao().findById(t.getPk()).get();
    	BeanUtil.copyIgnoreNull(t, source);
    	T updated = getBaseDao().save(source);
        return updated;
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

    
    @Override
    @Caching(evict = {
            @CacheEvict(value = CACHE_PAGE_NAME),
            @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()")
    })
//    @Caching(evict = {
//            @CacheEvict(value = CACHE_PAGE_NAME), @CacheEvict(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
//    })
    public void delete(T t) {
        getBaseDao().delete(t);
    }
}
