package com.unknown.library.backend.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private LocalDate published;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Author> authors;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<Borrow> readers;

    public void addBook(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
    }

    public void removeBook(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(published, book.published) && Objects.equals(authors, book.authors) && Objects.equals(readers, book.readers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, published, authors, readers);
    }
}
