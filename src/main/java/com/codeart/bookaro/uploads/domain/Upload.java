package com.codeart.bookaro.uploads.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor
public class Upload {
    String id;
    String fileName;
    byte[] file;
    String contentType;
    LocalDateTime createdAt;
}
