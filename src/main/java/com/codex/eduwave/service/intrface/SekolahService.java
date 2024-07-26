package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.response.SekolahResponse;
import org.springframework.data.domain.Page;

public interface SekolahService {

    SekolahResponse createSekolah(SekolahRequest request);
    SekolahResponse getById(String id);
}
