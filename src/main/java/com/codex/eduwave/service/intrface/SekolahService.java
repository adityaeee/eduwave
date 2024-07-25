package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SekolahRequest;
import org.springframework.data.domain.Page;

public interface SekolahService {

    Sekolah createSekolah(SekolahRequest request);
    Sekolah getById(String id);
}
