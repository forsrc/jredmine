package com.forsrc.jredmine.server.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseDao<T extends Serializable, PK> extends JpaRepository<T, PK>, PagingAndSortingRepository<T, PK> {
}
