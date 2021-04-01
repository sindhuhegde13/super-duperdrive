package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.CredentialsMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;
import com.nanodegree.superduperdrive.model.Credentials;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;


@Service
public class CredentialsService {

    private CredentialsMapper credentialsMapper;
    private UserMapper userMapper;
    private EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, UserMapper userMapper) {
        this.credentialsMapper = credentialsMapper;
        this.userMapper = userMapper;
    }

    public Credentials[] getCredentials(Integer userId) {
        return credentialsMapper.getCredentialByUser(userId);
    }
    public Credentials[] getCredentials(String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        return credentialsMapper.getCredentialByUser(userId);
    }

    public Credentials getCredential(Integer id) {
        return credentialsMapper.getCredentialById(id);
    }

    public void addCred(String url, String userName, String credUserName, String key, String password) {
        Integer userId = userMapper.getUser(userName).getUserId();
        Credentials credential = new Credentials(0, url, credUserName, key, password, userId);
        credentialsMapper.insertCredential(credential);
    }


    public void deleteCred(Integer credId) {
        credentialsMapper.deleteCredential(credId);
    }

    public void updateCred(Integer credentialId, String newUserName, String url, String key, String password) {
        credentialsMapper.updateCredential(credentialId, newUserName, url, key, password);
    }
}
