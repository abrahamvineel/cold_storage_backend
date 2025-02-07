package org.fileupload.service;

import lombok.AllArgsConstructor;
import org.fileupload.dto.FileUploadRequest;
import org.fileupload.model.File;
import org.fileupload.model.FileType;
import org.fileupload.repository.FileUploadRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class FileUploadService {

    private Set<FileType> blackList;

    private FileUploadRepository repository;

    public void uploadFile(FileUploadRequest fileUploadRequest) {
        File file = new File();
        if (!blackList.contains(fileUploadRequest.getFileType())) {
            file.setUserEmail(fileUploadRequest.getUserEmail());
            file.setFileSizeInBytes(fileUploadRequest.getFileSizeInBytes());
            file.setFileType(fileUploadRequest.getFileType());
            file.setUrl(fileUploadRequest.getUrl());
            file.setUploadDate(fileUploadRequest.getUploadDate());
            repository.save(file);

        }
    }
}
