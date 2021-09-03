package com.forsrc.jredmine.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.forsrc.jredmine.server.model.User;

@Mapper
public interface UserMapper {
    List<User> findByAuthority(@Param("authority") String authority);
    void updateJwtToken(@Param("username") String username, @Param("jwtToken") String jwtToken);
}
