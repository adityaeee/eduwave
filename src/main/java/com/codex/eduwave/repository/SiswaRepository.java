package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SiswaRepository extends JpaRepository<Siswa, String>, JpaSpecificationExecutor<Siswa> {
    Optional<Siswa> findByNis(String nis);
    List<Siswa> findByGolonganSekolahId(String sekolahId);
}
