package com.codeart.bookaro.uploads.db;

import com.codeart.bookaro.uploads.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadJpaRepository extends JpaRepository<Upload, Long> {
}
