package com.nanodegree.superduperdrive.model;

/**
 * The type Credential form.
 */
public class CredentialForm {

    private Integer credentialId;
    private String url;
    private String userName;
    private String password;

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
}
