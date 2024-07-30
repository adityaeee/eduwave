package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaksiRepository extends JpaRepository<Transaksi, String > {
}
