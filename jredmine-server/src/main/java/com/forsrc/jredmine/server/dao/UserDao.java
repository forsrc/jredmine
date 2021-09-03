package com.forsrc.jredmine.server.dao;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.forsrc.jredmine.server.model.User;


@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserDao extends BaseDao<User, String> {
    List<User> findByUsernameAndPassword(String username, String password);
}
