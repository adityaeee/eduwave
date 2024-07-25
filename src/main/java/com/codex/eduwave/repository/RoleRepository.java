package com.codex.eduwave.repository;

import com.codex.eduwave.constant.UserRole;
import com.codex.eduwave.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
