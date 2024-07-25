package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Sekolah;
import org.springframework.data.domain.Page;

public interface SekolahService {

    Sekolah createSekolah(Sekolah request);
    Sekolah getById(String id);
}
