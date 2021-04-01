package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserById(Integer userId);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    int insert(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    void deleteUser(String username);
}
