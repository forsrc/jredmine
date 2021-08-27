package com.forsrc.jredmine.server.dao.mapper;

import com.forsrc.jredmine.server.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> findByAuthority(@Param("authority") String authority);
}
