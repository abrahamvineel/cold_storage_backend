package org.fileupload.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.model.File;
import org.fileupload.model.FileType;
import org.fileupload.repository.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadService {

    private Set<FileType> blackList;

    private FileUploadRepository repository;

    private S3Service s3Service;

    public void uploadFile(FileUploadRequest fileUploadRequest, MultipartFile uploadedFile) {
        File file = new File();
        String fileName = s3Service.uploadFile(uploadedFile);
        String preSignedUrl = s3Service.generatePreSignedUrl(fileName);
        if (!blackList.contains(fileUploadRequest.getFileType())) {
            file.setUserEmail(fileUploadRequest.getUserEmail());
            file.setFileSizeInBytes(fileUploadRequest.getFileSizeInBytes());
            file.setFileType(fileUploadRequest.getFileType());
            file.setUrl(preSignedUrl);
            file.setUploadDate(new Date(System.currentTimeMillis()));
            file.setFileName(fileName);
            repository.save(file);
        }
    }
}
