package org.fileupload.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.fileupload.dto.FileDTO;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.model.FileType;
import org.fileupload.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {

    private FileUploadService fileUploadService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                           @RequestParam("metadata") String request) throws JsonProcessingException {


        ObjectMapper objectMapper = new ObjectMapper();
        FileUploadRequest r = objectMapper.readValue(request, FileUploadRequest.class);

        boolean isTokenValid = validateToken(r.getUserEmail());
        if (!isTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
        }

        String mimeType = file.getContentType();
        FileType fileType = FileType.mimeType(mimeType);
        r.setFileType(fileType);

        fileUploadService.uploadFile(r, file);

        return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded");
    }

    private boolean validateToken(String email) {
        try {
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:9193/user/validate-token?email=" + email)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }
    @GetMapping
    public ResponseEntity<?> getFiles(@RequestParam("email") String userEmail) {
        boolean isTokenValid = validateToken(userEmail);

        if (!isTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
        }

        List<FileDTO> files = fileUploadService.getFiles(userEmail);
        return ResponseEntity.ok(files);
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam("fileName") String fileName,
                                          @RequestParam("email") String email) {

        boolean isTokenValid = validateToken(email);
        if (!isTokenValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
        }

        String url = fileUploadService.downloadFile(fileName);
        return ResponseEntity.ok(url);
    }
}
