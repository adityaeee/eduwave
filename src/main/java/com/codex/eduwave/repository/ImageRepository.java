package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String > {
}
