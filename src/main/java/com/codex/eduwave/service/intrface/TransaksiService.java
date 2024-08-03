package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Transaksi;
import com.codex.eduwave.model.request.TransaksiRequest;
import com.codex.eduwave.model.request.UpdateTransaksiPembayaranStatusRequest;
import com.codex.eduwave.model.response.TransaksiResponse;
import com.codex.eduwave.model.response.TransaksiSiswaResponse;

import java.util.List;

public interface TransaksiService {

    TransaksiResponse create (TransaksiRequest request);

    List<TransaksiSiswaResponse> getAllByIdSiswa(String siswaId);

    Transaksi getById (String id);

    void updateStatus (UpdateTransaksiPembayaranStatusRequest request);

}
