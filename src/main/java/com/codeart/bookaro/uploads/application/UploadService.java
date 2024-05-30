package com.codeart.bookaro.uploads.application
        ;

import com.codeart.bookaro.uploads.application.port.UploadUseCase;
import com.codeart.bookaro.uploads.domain.Upload;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UploadService implements UploadUseCase {
    private final Map<String, Upload> storage = new ConcurrentHashMap<>();


    @Override
    public Upload save(SaveUploadCommand command) {
        String newId = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
        Upload upload = new Upload(
                newId,
                command.getFileName(),
                command.getFile(),
                command.getContentType(),
                LocalDateTime.now()
        );
        storage.put(upload.getId(), upload);
        System.out.println("File saved: " + upload.getFileName() + " with id: " + newId);
        return upload;
    }

    @Override
    public Optional<Upload> getById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void removeById(String id) {
        storage.remove(id);
    }
}
