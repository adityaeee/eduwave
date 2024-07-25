package com.codex.eduwave.service.intrface;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String create(MultipartFile file);
}
