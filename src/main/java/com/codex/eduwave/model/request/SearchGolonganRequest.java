package com.codex.eduwave.model.request;

import com.codex.eduwave.entity.Sekolah;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchGolonganRequest {
    private String name;
    private Integer spp;
}
