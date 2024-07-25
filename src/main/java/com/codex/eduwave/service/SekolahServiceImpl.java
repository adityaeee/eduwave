package com.codex.eduwave.service;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.repository.SekolahRepository;
import com.codex.eduwave.service.intrface.SekolahService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SekolahServiceImpl implements SekolahService {

    private final SekolahRepository sekolahRepository;
    @Override
    public Sekolah createSekolah(Sekolah request) {
        return sekolahRepository.saveAndFlush(request);
    }

    @Override
    public Sekolah getById(String id) {
        return null;
    }
}
