package com.unknown.library.backend.services;

import com.unknown.library.backend.models.Author;
import com.unknown.library.backend.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorService implements CrudListener<Author> {

    private final transient AuthorRepository repository;


    @Override
    public Collection<Author> findAll() {
        return repository.findAll();
    }


    public Author findByName(String authorName) {
        return repository.findByName(authorName);
    }

    @Override
    public Author add(Author author) {
        return repository.save(author);
    }

    @Override
    public Author update(Author author) {
        return repository.save(author);
    }

    @Override
    public void delete(Author author) {
        repository.delete(author);
    }
}
