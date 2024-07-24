package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

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
}
