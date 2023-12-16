package com.unknown.library.backend.services;

import com.unknown.library.backend.models.Book;
import com.unknown.library.backend.repositories.AuthorRepository;
import com.unknown.library.backend.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;
import java.util.HashSet;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService implements CrudListener<Book> {

    private final transient BookRepository bookRepository;
    private final transient AuthorRepository authorRepository;

    public Collection<Book> findByNameContainingIgnoreCase(String name) {
        return bookRepository.findAll(where(BookRepository.hasAuthorNameStartingIgnoreCase(name)));
    }

    @Override
    public Collection<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book add(Book book) {
        book.setAuthors(new HashSet<>(authorRepository.saveAll(book.getAuthors())));
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        book.setAuthors(new HashSet<>(authorRepository.saveAll(book.getAuthors())));
        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
