package com.codex.eduwave.service;

import com.codex.eduwave.entity.Image;
import com.codex.eduwave.repository.ImageRepository;
import com.codex.eduwave.service.intrface.ImageService;
import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageKit imageKit;
    private final ImageRepository imageRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Image create(MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            FileCreateRequest fileCreateRequest = new FileCreateRequest(file.getBytes(), fileName);
            Result result = imageKit.upload(fileCreateRequest);

            Image image = Image.builder()
                    .fileId(result.getFileId())
                    .url(result.getUrl())
                    .name(result.getName())
                    .size(result.getSize())
                    .fileType(result.getFileType())
                    .build();

            return imageRepository.saveAndFlush(image);

        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image", e);
        }
    }

    @Override
    public void deleteFromImageKit(String id) {
        Image image = imageRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        try {
            imageKit.deleteFile(image.getFileId());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    public void deleteFromEntity(String id) {
        Image image = imageRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));
        try {
            imageRepository.delete(image);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete image--");
        }
    }
}
