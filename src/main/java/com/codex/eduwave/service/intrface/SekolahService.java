package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SearchSekolahRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.request.UpdateSekolahRequest;
import com.codex.eduwave.model.response.SekolahResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SekolahService {
    Page<Sekolah> getAllSekolah(SearchSekolahRequest request);
    Sekolah createSekolah(SekolahRequest request);
    Sekolah getById(String id);
    Sekolah update(UpdateSekolahRequest sekolah);
    void delete(String id);
    Sekolah getSekolahByAccountId(String accountId);

}
