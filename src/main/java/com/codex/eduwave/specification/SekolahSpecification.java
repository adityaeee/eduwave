package com.codex.eduwave.specification;

import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SearchSekolahRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class SekolahSpecification {
    public static Specification<Sekolah> getSpesification(SearchSekolahRequest request){

        return ((root,query,criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    criteriaBuilder.equal(
                            root.get("isDeleted"),
                            false
                    )
            );

            if (request.getSekolah() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("sekolah")),
                                "%" + request.getSekolah() + "%"
                        )
                );
            }

            if (request.getNpsn() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("npsn"),
                                request.getNpsn()
                        )
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        });
    }
}
