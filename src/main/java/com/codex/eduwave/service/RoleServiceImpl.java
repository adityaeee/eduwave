package com.codex.eduwave.service;

import com.codex.eduwave.constant.UserRole;
import com.codex.eduwave.entity.Role;
import com.codex.eduwave.repository.RoleRepository;
import com.codex.eduwave.service.intrface.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role)
                .orElseGet(()-> roleRepository.saveAndFlush(Role.builder().role(role).build()));
    }
}
