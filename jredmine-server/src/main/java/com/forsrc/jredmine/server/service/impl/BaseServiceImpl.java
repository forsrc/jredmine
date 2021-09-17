package com.forsrc.jredmine.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.cache.CacheManager;

import com.forsrc.jredmine.server.model.BaseModel;
import com.forsrc.jredmine.server.service.BaseService;
import com.forsrc.jredmine.server.utils.BeanUtil;

@Service
@Transactional(rollbackFor = { Exception.class })
public abstract class BaseServiceImpl<T extends BaseModel<PK>, PK> implements BaseService<T, PK> {

    public static final String CACHE_NAME = "jredmine";
    public static final String CACHE_PAGE_NAME = CACHE_NAME + "/page";

    private static final Logger LOG = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Autowired
    CacheManager cacheManager;

    @Override
    @Caching(evict = { @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()") })
    public T save(T t) {
        T saved = getBaseDao().save(t);

        removePageCache();

        return saved;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()") })
    public T update(T t) {
        T source = getBaseDao().findById(t.getPk()).get();
        BeanUtil.copyIgnoreNull(t, source);
        T updated = getBaseDao().save(source);

        removePageCache();

        return updated;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #pk")
    // @CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    public T get(PK pk) {
        return getBaseDao().findById(pk).get();
    }

    @Override
    @Cacheable(value = CACHE_PAGE_NAME, key = "#root.targetClass.getName() + '/page/' + #page + '-' + #size")
    // @CachePut(value = CACHE_NAME, keyGenerator = "myKeyGenerator")
    public Page<T> page(int page, int size) {
        Page<T> p = getBaseDao().findAll(PageRequest.of(page, size));
        return p;
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #pk") })
    public void delete(PK pk) {
        getBaseDao().deleteById(pk);
        removePageCache();
    }

    @Override
    @Caching(evict = { @CacheEvict(value = CACHE_NAME, key = "#root.targetClass.getName() + '/' + #t.getPk()") })
    public void delete(T t) {
        getBaseDao().delete(t);
        removePageCache();
    }

    public void removePageCache() {
        cacheManager.getCache(CACHE_PAGE_NAME).forEach((e) -> {
            String key = e.getKey().toString();
            for (String removeKey : removeKeys()) {
                if (key.startsWith(removeKey)) {
                    LOG.debug("[CACHE]\tREMOVE\tKey = {}", e.getKey());
                    cacheManager.getCache(CACHE_PAGE_NAME).remove(e.getKey());
                }
            }
        });
    }

    public String[] removeKeys() {
        return new String[] { getClass().getName() };
    }
}
