package org.fileupload.controller;

import lombok.AllArgsConstructor;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.service.FileUploadService;
import org.fileupload.service.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class FileUploadController {

    private FileUploadService fileUploadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(
             @RequestParam("file") MultipartFile file,
                           @RequestParam("metadata") FileUploadRequest request) {
        fileUploadService.uploadFile(request, file);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadFile(@RequestParam("file") MultipartFile file) {
//    }
}
