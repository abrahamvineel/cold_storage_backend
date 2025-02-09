package org.fileupload.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
                           @RequestParam("metadata") String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileUploadRequest r = objectMapper.readValue(request, FileUploadRequest.class);

        fileUploadService.uploadFile(r, file);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void uploadFile(@RequestParam("file") MultipartFile file) {
//    }
}
