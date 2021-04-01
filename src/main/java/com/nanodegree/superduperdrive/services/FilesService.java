package com.nanodegree.superduperdrive.services;

import com.nanodegree.superduperdrive.mapper.FilesMapper;
import com.nanodegree.superduperdrive.mapper.UserMapper;


import com.nanodegree.superduperdrive.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

@Service
public class FilesService {

    private FilesMapper filesMapper;
    private UserMapper userMapper;

    public FilesService(FilesMapper filesMapper, UserMapper userMapper) {
        this.filesMapper = filesMapper;
        this.userMapper = userMapper;
    }

    public Files getFile(String file) { return filesMapper.getFiles(file); }

    public void deleteFiles(String name) {  filesMapper.deleteFiles(name); }

    public Files[] getFiles(Integer userId) { return filesMapper.getFilesByUser(userId); }

    public Files getFilesById(Integer fileId) { return filesMapper.getFilesById(fileId);}

    public Files[] getFilesByName(String username) {
        Integer user = userMapper.getUser(username).getUserId();
        return filesMapper.getFilesByUser(user);
    };

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
