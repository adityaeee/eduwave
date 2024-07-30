package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import com.codex.eduwave.constant.StatusSPP;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.SISWA)
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "NIS", nullable = false, unique = true)
    private String nis;

    @Column(name = "email")
    private String email;

    @Column(name = "no_hp", nullable = false)
    private String noHp;

    @Column(name = "no_hp_ortu")
    private String noHpOrtu;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusSPP status;

    @Column(name = "tagihan")
    private Integer tagihan;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "golongan_id", nullable = false)
    @JsonBackReference
    private Golongan golongan;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    private Date updatedAt;


}
