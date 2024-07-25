package com.codex.eduwave.service;

import com.codex.eduwave.service.intrface.FileService;
import io.imagekit.sdk.ImageKit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ImageKit imageKit;
    @Override
    public String create(MultipartFile file) {
        return "";
    }
}
