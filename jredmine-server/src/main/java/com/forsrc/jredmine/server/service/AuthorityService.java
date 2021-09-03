package com.forsrc.jredmine.server.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.model.AuthorityPk;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface AuthorityService extends BaseService<Authority, AuthorityPk>{

    @Transactional(readOnly = true)
    public List<Authority> getByUsername(String username);

    @Transactional(readOnly = true)
    public List<Authority> save(List<Authority> list);

    @Transactional(readOnly = true)
    public List<Authority> update(List<Authority> list);

    @Transactional()
    public void delete(String username);

}
