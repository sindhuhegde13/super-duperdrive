package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.User;
import org.apache.ibatis.annotations.*;

/**
 * The interface User mapper.
 */
@Mapper
public interface UserMapper {

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserById(Integer userId);

    /**
     * Insert int.
     *
     * @param user the user
     * @return the int
     */
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true,keyProperty = "userId")
    int insert(User user);

    /**
     * Delete user.
     *
     * @param username the username
     */
    @Delete("DELETE FROM USERS WHERE username = #{username}")
    void deleteUser(String username);
}
