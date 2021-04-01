package com.nanodegree.superduperdrive.model;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private MultipartFile fileUpload;

    public MultipartFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(MultipartFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
