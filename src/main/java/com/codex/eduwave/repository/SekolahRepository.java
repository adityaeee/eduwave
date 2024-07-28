package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SekolahRepository  extends JpaRepository<Sekolah,String> {
    List<Sekolah> findByIsDeletedFalse();
    Optional<Sekolah> findByAccountId(String accountId);

}
