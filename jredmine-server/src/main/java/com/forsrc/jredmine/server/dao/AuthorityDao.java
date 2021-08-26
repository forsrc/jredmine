package com.forsrc.jredmine.server.dao;

import com.forsrc.jredmine.server.model.Authority;
import com.forsrc.jredmine.server.model.AuthorityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(collectionResourceRel = "authority", path = "authority")
public interface AuthorityDao
        extends JpaRepository<Authority, AuthorityPk>, PagingAndSortingRepository<Authority, AuthorityPk> {

}
