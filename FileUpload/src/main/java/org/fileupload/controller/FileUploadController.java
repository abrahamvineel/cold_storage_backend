package org.fileupload.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.service.FileUploadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {

    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("metadata") String request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileUploadRequest r = objectMapper.readValue(request, FileUploadRequest.class);

        fileUploadService.uploadFile(r, file);
    }

    @GetMapping
    public List<String> getFiles(@RequestParam("email") String userEmail) {
        fileUploadService.getFiles(userEmail);
        return Collections.EMPTY_LIST;
    }
}
