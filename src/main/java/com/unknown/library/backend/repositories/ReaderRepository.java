package com.unknown.library.backend.repositories;

import com.unknown.library.backend.models.Borrow;
import com.unknown.library.backend.models.Reader;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface ReaderRepository extends JpaRepository<Reader, Long>, JpaSpecificationExecutor<Reader> {
    static Specification<Reader> violatesReturningTime(LocalDate currentDateMinus30days) {
        return (root, query, criteriaBuilder) -> {
            Join<Reader, Borrow> readersWithBorrowing = root.join("borrowBooks");
            return criteriaBuilder.lessThanOrEqualTo(readersWithBorrowing.get("borrowTime"), currentDateMinus30days);
        };
    }

}
