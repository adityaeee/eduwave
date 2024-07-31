package com.codex.eduwave.service.intrface;

import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
import com.codex.eduwave.model.response.TransaksiResponse;

import java.util.List;

public interface TransaksiService {

    TransaksiResponse create (TransaksiRequest request);

    List<TransaksiResponse> getAll();

    void updateStatus (UpdateTransaksiPembayaranStatusRequest request);

}
