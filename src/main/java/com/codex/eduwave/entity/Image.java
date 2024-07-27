package com.codex.eduwave.entity;

import com.codex.eduwave.constant.NameTable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = NameTable.IMAGE)
public class Image {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String url;

    @Column(name = "size")
    private Long size;

    @Column(name = "file_type")
    private String fileType;
}
