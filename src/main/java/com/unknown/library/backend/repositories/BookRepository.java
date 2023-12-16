package com.unknown.library.backend.repositories;

import com.unknown.library.backend.models.Author;
import com.unknown.library.backend.models.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    /**
     * @param authorName author name
     * @return specification for returning only {@link Book}s that contain {@link Author}s that has names started as authorName
     */
    static Specification<Book> hasAuthorNameStartingIgnoreCase(String authorName) {
        return (book, query, builder) -> {
            query.distinct(true);
            return builder.like(builder.upper(book.get("authors").get("name")), "%" + authorName.toUpperCase() + "%");
        };
    }

}
