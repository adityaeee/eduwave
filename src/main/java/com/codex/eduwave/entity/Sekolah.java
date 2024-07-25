package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.SEKOLAH)
public class Sekolah {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "sekolah", nullable = false)
    private String sekolah;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "no_hp", nullable = false)
    private String noHp;

    @Column(name = "NPSN", nullable = false)
    private String npsn;

    @Column(name = "surat_izin_operasional", nullable = false)
    private String suratIzinOperasional;

    @Column(name = "sertifikat_akreditasi")
    private String sertifikatAkreditasi;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "sekolah")
    @JsonManagedReference
    private List<Golongan> golonganSekolah;

    @OneToOne
    @JoinColumn(name = "account_id")
    private UserAccount user;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "updated_at")
    private Date updatedAt;
}
