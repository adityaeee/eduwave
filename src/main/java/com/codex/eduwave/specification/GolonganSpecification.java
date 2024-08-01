package com.codex.eduwave.specification;

import com.codex.eduwave.entity.Golongan;
import com.codex.eduwave.entity.Sekolah;
import com.codex.eduwave.model.request.SearchGolonganRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GolonganSpecification {

    public static Specification<Golongan> getSpesification(SearchGolonganRequest request){
        return  (((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    criteriaBuilder.equal(
                            root.get("isDeleted"),
                            false
                    )
            );

            predicates.add(
                    criteriaBuilder.equal(
                            root.get("sekolah").get("id"),
                            request.getSekolahId()
                    )
            );

            if (request.getName() != null){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("golongan")),
                                "%" + request.getName() + "%"
                        )
                );
            }

            if (request.getSpp() != null){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("spp"),
                                request.getSpp()
                        )
                );
            }


            return query.where(predicates.toArray(new  Predicate[]{})).getRestriction();


        }));
    }
}
