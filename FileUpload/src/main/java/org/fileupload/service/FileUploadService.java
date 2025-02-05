package org.fileupload.service;

import org.fileupload.dto.FileUploadRequest;
import org.fileupload.repository.FileUploadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    private List<String> blackList = new ArrayList<>();

    private FileUploadRepository repository;

    public void uploadFile(FileUploadRequest fileUploadRequest) {

    }
}
