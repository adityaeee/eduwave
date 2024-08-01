package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaksiRepository extends JpaRepository<Transaksi, String > {
    List<Transaksi> findBySiswaId(String siswaId);
}
