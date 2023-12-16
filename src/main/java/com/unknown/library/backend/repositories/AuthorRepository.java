package com.unknown.library.backend.repositories;

import com.unknown.library.backend.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    Author findByName(String name);

}
