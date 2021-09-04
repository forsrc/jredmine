package com.forsrc.jredmine.server.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.dao.BaseDao;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface BaseService<T extends com.forsrc.jredmine.server.model.Cacheable<PK>, PK> {



    @Transactional(readOnly = true)
    public T get(PK pk);

    @Transactional(rollbackFor = { Exception.class })
    public T save(T t);

    @Transactional(rollbackFor = { Exception.class })
    public T update(T t);

    @Transactional(readOnly = true)
    public Page<T> page(int page, int size);

    @Transactional()
    public void delete(PK pk);

    @Transactional()
    public void delete(T t);
  
    public BaseDao<T, PK> getBaseDao();
}
