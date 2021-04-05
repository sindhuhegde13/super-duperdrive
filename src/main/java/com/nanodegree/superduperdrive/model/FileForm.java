package com.nanodegree.superduperdrive.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * The type File form.
 */
public class FileForm {

    private String username;

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private MultipartFile fileUpload;

    /**
     * Gets file upload.
     *
     * @return the file upload
     */
    public MultipartFile getFileUpload() {
        return fileUpload;
    }

    /**
     * Sets file upload.
     *
     * @param fileUpload the file upload
     */
    public void setFileUpload(MultipartFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    private String filename;

    /**
     * Gets filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Sets filename.
     *
     * @param filename the filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}
