package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image create(MultipartFile file);
    void deleteFromImageKit(String id);
    void deleteFromEntity(String id);
}
