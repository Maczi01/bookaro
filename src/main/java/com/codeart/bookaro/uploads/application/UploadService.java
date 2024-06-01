package com.codeart.bookaro.uploads.application
        ;

import com.codeart.bookaro.uploads.application.port.UploadUseCase;
import com.codeart.bookaro.uploads.db.UploadJpaRepository;
import com.codeart.bookaro.uploads.domain.Upload;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class UploadService implements UploadUseCase {
    private final Map<String, Upload> storage = new ConcurrentHashMap<>();
    private final UploadJpaRepository repository;

    @Override
    public Upload save(SaveUploadCommand command) {
        String newId = RandomStringUtils.randomAlphanumeric(8)
                .toLowerCase();
        Upload upload = new Upload(
                command.getFileName(),
                command.getFile(),
                command.getContentType()
        );
        repository.save(upload);
        System.out.println("File saved: " + upload.getFileName() + " with id: " + newId);
        return upload;
    }

    @Override
    public Optional<Upload> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
