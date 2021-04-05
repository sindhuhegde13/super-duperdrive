package com.nanodegree.superduperdrive.mapper;

import com.nanodegree.superduperdrive.model.Credentials;
import org.apache.ibatis.annotations.*;

/**
 * The interface Credentials mapper.
 */
@Mapper
public interface CredentialsMapper {

    /**
     * Gets credential by id.
     *
     * @param credentialId the credential id
     * @return the credential by id
     */
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credentials getCredentialById(Integer credentialId);

    /**
     * Get credential by user credentials [ ].
     *
     * @param userId the user id
     * @return the credentials [ ]
     */
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    Credentials[] getCredentialByUser(Integer userId);

    /**
     * Insert credential int.
     *
     * @param credential the credential
     * @return the int
     */
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credentials credential);

    /**
     * Delete credential.
     *
     * @param credentialId the credential id
     */
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

    /**
     * Update credential.
     *
     * @param credentialId the credential id
     * @param url          the url
     * @param newUserName  the new user name
     * @param key          the key
     * @param password     the password
     */
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{newUserName}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
    void updateCredential(Integer credentialId, String url, String newUserName, String key, String password);
}
