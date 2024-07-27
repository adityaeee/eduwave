package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SiswaRequest;

public interface SiswaService {
    Siswa create(SiswaRequest request);
    Siswa getById(String id);
}
