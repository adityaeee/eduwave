package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiswaRepository extends JpaRepository<Siswa, String> {

}
