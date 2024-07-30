package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.TRANSAKSI)
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "siswa_id")
    private Siswa siswa;

    @Column(name = "jumlah_bayar")
    private Integer jumlahBayar;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "trans_date", updatable = false)
    private Date transDate;

    @OneToOne
    @JoinColumn(name = "pembayaran_id", unique = true)
    private Pembayaran pembayaran;
}