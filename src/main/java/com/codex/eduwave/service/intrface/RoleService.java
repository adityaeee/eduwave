package com.codex.eduwave.service.intrface;

import com.codex.eduwave.constant.UserRole;
import com.codex.eduwave.entity.Role;

public interface RoleService {
    Role getOrSave (UserRole role);
}
