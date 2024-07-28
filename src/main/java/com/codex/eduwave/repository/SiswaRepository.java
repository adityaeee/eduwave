package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.entity.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SiswaRepository extends JpaRepository<Siswa, String>, JpaSpecificationExecutor<Siswa> {
    List<Siswa> findByIsActiveFalse();
}
