package com.forsrc.jredmine.server.service;

import com.forsrc.jredmine.server.dao.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface BaseService<T extends Serializable, PK> {



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

    public BaseDao<T, PK> getBaseDao();
}
