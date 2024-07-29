package com.codex.eduwave.specification;

import com.codex.eduwave.entity.Siswa;
import com.codex.eduwave.model.request.SearchSiswaRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SiswaSpecification {
    public static Specification<Siswa> getSpecification (SearchSiswaRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate sekolahPredicate = criteriaBuilder.equal(root.get("golongan").get("sekolah").get("id"), request.getSekolahId());
            predicates.add(sekolahPredicate);

            if (request.getNama() != null) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nama")), "%" + request.getNama().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getNis() != null) {
                Predicate nisPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nis")), "%" + request.getNis().toLowerCase() + "%");
                predicates.add(nisPredicate);
            }

            if (request.getIsActive() != null) {
                Predicate isActivePredicate = criteriaBuilder.equal(root.get("isActive"), request.getIsActive());
                predicates.add(isActivePredicate);
            }

            if (request.getStatus() != null) {
                Predicate statusPredicate = criteriaBuilder.equal(criteriaBuilder.lower(root.get("status")), request.getStatus());
                predicates.add(statusPredicate);
            }

            if (request.getTagihan() != null) {
                Predicate tagihanPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("tagihan"), request.getTagihan());
                predicates.add(tagihanPredicate);
            }

            if (request.getGolongan() != null) {
                Predicate golonganPredicate = criteriaBuilder.equal(root.get("golongan").get("id"), request.getGolongan());
                predicates.add(golonganPredicate);
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
