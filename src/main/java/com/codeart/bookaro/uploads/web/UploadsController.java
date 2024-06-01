package com.codeart.bookaro.uploads.web;

import com.codeart.bookaro.uploads.application.port.UploadUseCase;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/uploads")
public class UploadsController {

    private final UploadUseCase upload;

    @GetMapping("/{id}")
    public ResponseEntity<UploadReponse> getUpload(@PathVariable Long id) {
        return upload
                .getById(id)
                .map(file -> {
                    UploadReponse uploadReponse = new UploadReponse(
                            file.getId(),
                            file.getContentType(),
                            file.getFileName(),
                            file.getCreatedAt()
                    );
                    return ResponseEntity.ok(uploadReponse);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getUploadFile(@PathVariable Long id) {
        return upload
                .getById(id)
                .map(file -> {
                    String contentDisposition = "attachment; filename=\"" + file.getFileName() + "\"";
                    Resource resource = new ByteArrayResource(file.getFile());
                    return ResponseEntity
                            .ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                            .contentType(MediaType.parseMediaType(file.getContentType()))
                            .body(resource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Value
    @AllArgsConstructor
    static class UploadReponse {
        Long id;
        String contentType;
        String fileName;
        LocalDateTime createdAt;
    }

}
