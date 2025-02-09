package org.fileupload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fileupload.model.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {

    private long id;
    private String userEmail;
    private int fileSizeInBytes;
    private String fileType;
}
