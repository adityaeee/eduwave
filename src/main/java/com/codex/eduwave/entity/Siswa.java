package com.codex.eduwave.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
