package com.forsrc.jredmine.server.dao;

import org.springframework.stereotype.Repository;

import com.forsrc.jredmine.server.model.UserDetails;


@Repository
//@RepositoryRestResource(collectionResourceRel = "user_details", path = "user_details")
public interface UserDetailsDao extends BaseDao<UserDetails, String> {
    UserDetails findByUsername(String username);
}
