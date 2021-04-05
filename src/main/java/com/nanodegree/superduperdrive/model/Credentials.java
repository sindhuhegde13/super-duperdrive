package com.nanodegree.superduperdrive.model;

/**
 * The type Credentials.
 */
public class Credentials {
    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;

    /**
     * Instantiates a new Credentials.
     *
     * @param credentialId the credential id
     * @param url          the url
     * @param userName     the user name
     * @param key          the key
     * @param password     the password
     * @param userId       the user id
     */
    public Credentials(Integer credentialId, String url, String userName, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    /**
     * Instantiates a new Credentials.
     *
     * @param url      the url
     * @param username the username
     * @param password the password
     */
    public Credentials(String url, String username, String password) {
        this.url = url;
        this.userName = username;
        this.password = password;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets credential id.
     *
     * @return the credential id
     */
    public Integer getCredentialId() {
        return credentialId;
    }

    /**
     * Sets credential id.
     *
     * @param credentialId the credential id
     */
    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }
}
