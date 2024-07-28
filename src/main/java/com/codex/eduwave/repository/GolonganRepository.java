package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GolonganRepository extends JpaRepository<Golongan, String> {
    List<Golongan> findByIsDeletedFalse();
}
