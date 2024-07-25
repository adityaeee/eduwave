package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SekolahRepository  extends JpaRepository<Sekolah,String> {
}
