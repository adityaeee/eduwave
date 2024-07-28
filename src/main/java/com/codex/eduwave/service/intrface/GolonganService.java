package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.model.request.GolonganRequest;
import com.codex.eduwave.model.request.UpdateGolonganRequest;

import java.util.List;

public interface GolonganService {
    Golongan create(GolonganRequest request);
    Golongan getById(String id);
    List<Golongan> getAll();
    Golongan update(UpdateGolonganRequest request);
    void delete(String id);
}
