package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SearchSekolahRequest;
import com.codex.eduwave.model.request.SekolahRequest;
import com.codex.eduwave.model.request.UpdateSekolahRequest;
import com.codex.eduwave.model.response.SekolahByIdResponse;
import org.springframework.data.domain.Page;

public interface SekolahService {
    Page<Sekolah> getAllSekolah(SearchSekolahRequest request);
    Sekolah createSekolah(SekolahRequest request);
    Sekolah getByNpsn(String npsn);
    Sekolah update(UpdateSekolahRequest sekolah);
    void delete(String id);
    Sekolah getSekolahByAccountId(String accountId);

}
