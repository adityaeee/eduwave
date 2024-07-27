package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.model.request.GolonganRequest;

public interface GolonganService {
    Golongan create(GolonganRequest request);
    Golongan getById(String id);
}
