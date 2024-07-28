package com.codex.eduwave.repository;

import com.codex.eduwave.entity.Account;
import com.codex.eduwave.entity.Sekolah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByUsername(String username);
}
