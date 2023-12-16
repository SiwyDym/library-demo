package com.unknown.library.backend.repositories;

import com.unknown.library.backend.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {


     Borrow save();

}
