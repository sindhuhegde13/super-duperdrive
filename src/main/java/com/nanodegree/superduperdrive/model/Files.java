package com.nanodegree.superduperdrive.model;

/**
 * The type Files.
 */
public class Files {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;

    /**
     * Instantiates a new Files.
     *
     * @param fileId      the file id
     * @param fileName    the file name
     * @param contentType the content type
     * @param fileSize    the file size
     * @param userId      the user id
     * @param fileData    the file data
     */
    public Files(Integer fileId, String fileName, String contentType, String fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    /**
     * Gets file id.
     *
     * @return the file id
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * Sets file id.
     *
     * @param fileId the file id
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets content type.
     *
     * @return the content type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Sets content type.
     *
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Gets file size.
     *
     * @return the file size
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Sets file size.
     *
     * @param fileSize the file size
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
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
     * Get file data byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * Sets file data.
     *
     * @param fileData the file data
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
