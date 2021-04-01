package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Credentials;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credentials getCredentialById(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credentials[] getCredentialByUser(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credentials credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{newUserName}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
    void updateCredential(Integer credentialId, String url, String newUserName, String key, String password);
}
