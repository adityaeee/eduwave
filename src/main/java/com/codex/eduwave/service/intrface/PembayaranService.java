package com.codex.eduwave.service.intrface;

import com.codex.eduwave.entity.Pembayaran;
import com.codex.eduwave.entity.Transaksi;

public interface PembayaranService {
    Pembayaran createPembayaran (Transaksi transaksi);
}
