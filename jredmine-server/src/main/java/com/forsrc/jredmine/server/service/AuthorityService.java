package com.forsrc.jredmine.server.service;

import java.util.List;

import com.forsrc.jredmine.server.model.Authority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = { Exception.class })
public interface AuthorityService {

    @Transactional(readOnly = true)
    public List<Authority> getByUsername(String username);


    public List<Authority> save(List<Authority> list);


    public List<Authority> update(List<Authority> list);

    public void delete(String username);

}
