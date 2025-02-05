package org.fileupload.repository;

import org.fileupload.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<File, Long> {
}
