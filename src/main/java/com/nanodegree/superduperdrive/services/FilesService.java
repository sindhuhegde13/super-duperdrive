package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.FilesMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;


import com.nanodegree.superduperdrive.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * The type Files service.
 */
@Service
public class FilesService {

    private FilesMapper filesMapper;
    private UserMapper userMapper;

    /**
     * Instantiates a new Files service.
     *
     * @param filesMapper the files mapper
     * @param userMapper  the user mapper
     */
    public FilesService(FilesMapper filesMapper, UserMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    /**
     * Gets file.
     *
     * @param file the file
     * @return the file
     */
    public Files getFile(String file) { return filesMapper.getFiles(file); }

    /**
     * Delete files.
     *
     * @param name the name
     */
    public void deleteFiles(String name) {  filesMapper.deleteFiles(name); }

    /**
     * Get files files [ ].
     *
     * @param userId the user id
     * @return the files [ ]
     */
    public Files[] getFiles(Integer userId) { return filesMapper.getFilesByUser(userId); }

    /**
     * Gets files by id.
     *
     * @param fileId the file id
     * @return the files by id
     */
    public Files getFilesById(Integer fileId) { return filesMapper.getFilesById(fileId);}

    /**
     * Get files by name files [ ].
     *
     * @param username the username
     * @return the files [ ]
     */
    public Files[] getFilesByName(String username) {
        Integer user = userMapper.getUser(username).getUserId();
        return filesMapper.getFilesByUser(user);
    };

    /**
     * Is file present boolean.
     *
     * @param file     the file
     * @param username the username
     * @return the boolean
     */
    public boolean isFilePresent(MultipartFile file, String username) {
        Integer userId = userMapper.getUser(username).getUserId();
        Files[] files = filesMapper.getFilesByUser(userId);
        for ( Files files1 : files ) {
            if(Objects.equals(file.getOriginalFilename(), files1.getFileName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Upload file.
     *
     * @param multipartFile the multipart file
     * @param username      the username
     * @throws IOException the io exception
     */
    public void uploadFile(MultipartFile multipartFile, String username) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(username).getUserId();
        byte[] fileData = multipartFile.getBytes();
        Files files = new Files(0,fileName,contentType,fileSize,userId,fileData);
        filesMapper.insertFiles(files);
    }

}
