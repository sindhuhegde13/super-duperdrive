package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.CredentialsMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;
import com.nanodegree.superduperdrive.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


/**
 * The type Credentials service.
 */
@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private UserMapper userMapper;
    private EncryptionService encryptionService;

    /**
     * Instantiates a new Credentials service.
     *
     * @param credentialsMapper the credentials mapper
     * @param userMapper        the user mapper
     */
    public CredentialsService(CredentialsMapper credentialsMapper, UserMapper userMapper) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
    }

    /**
     * Get credentials credentials [ ].
     *
     * @param userId the user id
     * @return the credentials [ ]
     */
    public Credentials[] getCredentials(Integer userId) {
        return credentialsMapper.getCredentialByUser(userId);
    }

    /**
     * Get credentials credentials [ ].
     *
     * @param username the username
     * @return the credentials [ ]
     */
    public Credentials[] getCredentials(String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        return credentialsMapper.getCredentialByUser(userId);
    }

    /**
     * Gets credential.
     *
     * @param id the id
     * @return the credential
     */
    public Credentials getCredential(Integer id) {
        return credentialsMapper.getCredentialById(id);
    }

    /**
     * Add cred.
     *
     * @param url          the url
     * @param userName     the user name
     * @param credUserName the cred user name
     * @param key          the key
     * @param password     the password
     */
    public void addCred(String url, String userName, String credUserName, String key, String password) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Credentials credential = new Credentials(0, url, credUserName, key, password, userId);
        credentialsMapper.insertCredential(credential);
    }


    /**
     * Delete cred.
     *
     * @param credId the cred id
     */
    public void deleteCred(Integer credId) {
        credentialsMapper.deleteCredential(credId);
    }

    /**
     * Update cred.
     *
     * @param credentialId the credential id
     * @param url          the url
     * @param newUserName  the new user name
     * @param key          the key
     * @param password     the password
     */
    public void updateCred(Integer credentialId, String url, String newUserName, String key, String password) {
        credentialsMapper.updateCredential(credentialId, url, newUserName, key, password);
    }
}
