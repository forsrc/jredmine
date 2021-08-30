package com.forsrc.jredmine.server.dao;

import com.forsrc.jredmine.server.model.User;
import com.forsrc.jredmine.server.model.UserDetails;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
//@RepositoryRestResource(collectionResourceRel = "user_details", path = "user_details")
public interface UserDetailsDao extends BaseDao<UserDetails, String> {
    UserDetails findByUsername(String username);
}
