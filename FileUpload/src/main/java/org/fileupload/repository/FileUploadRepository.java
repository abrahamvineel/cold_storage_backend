package org.fileupload.repository;

import org.fileupload.dto.FileDTO;
import org.fileupload.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadRepository extends JpaRepository<File, Long> {
    @Query("select new org.fileupload.dto.FileDTO(f.fileName, f.uploadDate, f.fileSizeInBytes) from File f where f.userEmail = :userEmail")
    List<FileDTO> findFileByUserEmail(@Param("userEmail") String userEmail);
}
