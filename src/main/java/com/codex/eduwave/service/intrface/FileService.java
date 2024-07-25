package com.codex.eduwave.service.intrface;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String create(MultipartFile file);
}
