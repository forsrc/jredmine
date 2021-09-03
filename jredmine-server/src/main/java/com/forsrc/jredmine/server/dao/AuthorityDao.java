package com.forsrc.jredmine.server.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.model.AuthorityPk;


@Repository
@RepositoryRestResource(collectionResourceRel = "authority", path = "authority")
public interface AuthorityDao
        extends BaseDao<Authority, AuthorityPk> {

}
