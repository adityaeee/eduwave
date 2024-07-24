package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.GOLONGAN)
public class Golongan {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "golongan", nullable = false)
    private String golongan;

    @Column(name = "SPP", nullable = false)
    private Integer spp;

    @ManyToOne
    @JoinColumn(name = "sekolah_id", nullable = false)
    @JsonBackReference
    private Sekolah sekolah;

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
