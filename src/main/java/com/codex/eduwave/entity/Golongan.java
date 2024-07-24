package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import com.codex.eduwave.constant.StatusSPP;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

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
}
