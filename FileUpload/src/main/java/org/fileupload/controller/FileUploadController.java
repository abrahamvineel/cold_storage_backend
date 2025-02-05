package org.fileupload.controller;

import lombok.AllArgsConstructor;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class FileUploadController {

    private FileUploadService fileUploadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestBody FileUploadRequest fileUploadRequest) {
        fileUploadService.uploadFile(fileUploadRequest);
    }
}
