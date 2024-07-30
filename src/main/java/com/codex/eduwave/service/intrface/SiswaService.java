package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SearchSiswaRequest;
import com.codex.eduwave.model.request.SiswaRequest;
import com.codex.eduwave.model.request.UpdateSiswaRequest;
import com.codex.eduwave.model.response.SiswaResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SiswaService {
    Siswa create(SiswaRequest request);
    Siswa getById(String id);
    Siswa getByNis(String nis);
    Page<Siswa> getAll(SearchSiswaRequest request);
    Siswa update(UpdateSiswaRequest request);
    void inActive(String id);
    List<Siswa> updateStatusBulk(List<Siswa> requests);

}
