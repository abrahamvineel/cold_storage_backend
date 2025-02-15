package org.fileupload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fileupload.model.FileType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {

    private long id;
    private String userEmail;
    private int fileSizeInBytes;
    @JsonIgnore
    private FileType fileType;
}
