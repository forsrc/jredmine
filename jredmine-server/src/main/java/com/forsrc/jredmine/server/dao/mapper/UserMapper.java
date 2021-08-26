package com.forsrc.jredmine.server.dao.mapper;

import com.forsrc.jredmine.server.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsernameAndPassword(String username, String password);
}
