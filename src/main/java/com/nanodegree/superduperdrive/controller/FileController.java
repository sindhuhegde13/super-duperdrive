package com.nanodegree.superduperdrive.controller;

import com.nanodegree.superduperdrive.model.FileForm;
import com.nanodegree.superduperdrive.model.Files;
import com.nanodegree.superduperdrive.services.FilesService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class FileController {

    private FilesService filesService;

    public FileController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public String saveFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model) throws IOException {
        String username = authentication.getName();
        if(fileForm.getFileUpload().getSize() == 0) {
            model.addAttribute("result","error");
            model.addAttribute("message","You uploaded an empty file.");
            return "result";
        }

        if(filesService.isFilePresent(fileForm.getFileUpload(),username)) {
            model.addAttribute("result","error");
            model.addAttribute("message","You are trying to add a duplicate file. Please try again");
            return "result";
        }
        fileForm.setUsername(authentication.getName());
        filesService.uploadFile(fileForm.getFileUpload(), username);
        model.addAttribute("allFiles",filesService.getFilesByName(username));
        model.addAttribute("result","success");
        return "result";
    }

    @GetMapping("/viewfile")
    public ResponseEntity<InputStreamResource> downloadFile(
            @RequestParam(required = false, name = "fileId") Integer fileId) {
        Files file = this.filesService.getFilesById(fileId);
        String fileName = file.getFileName();
        String contentType = file.getContentType();
        byte[] fileData = file.getFileData();
        InputStream inputStream = new ByteArrayInputStream(fileData);
        InputStreamResource resource = new InputStreamResource(inputStream);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/deletefile")
    public String deleteFile(@RequestParam(required = false, name = "fileId") Integer fileId,Model model) {
        Files files = filesService.getFilesById(fileId);
        filesService.deleteFiles(files.getFileName());
        model.addAttribute("result","success");
        return "result";
    }
}
