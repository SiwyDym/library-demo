package com.unknown.library.ui;

import com.unknown.library.backend.models.Author;
import com.unknown.library.backend.models.Book;
import com.unknown.library.backend.services.AuthorService;
import com.unknown.library.backend.services.BookService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.impl.field.provider.DefaultFieldProvider;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Route("")
public class HomeView extends VerticalLayout {

    private static final String AUTHOR_DELIMITER = ",";

    public HomeView(BookService bookService, AuthorService authorService) {
        var bookGrid = new GridCrud<>(Book.class, bookService);

        createFilterComponent(bookService, bookGrid);
        handleConversionOfAuthorsInGrid(authorService, bookGrid);

        bookGrid.getGrid().setColumns("title", "published", "authors");
        bookGrid.getCrudFormFactory().setVisibleProperties("title", "published", "authors");
        bookGrid.getCrudFormFactory().setUseBeanValidation(true);

        add(
                new H1("My library"),
                bookGrid
        );
    }

    private static void handleConversionOfAuthorsInGrid(AuthorService authorService, GridCrud<Book> bookGrid) {
        bookGrid.getCrudFormFactory().setConverter("authors", new Converter<String, Set<Author>>() {
            @Override
            public Result<Set<Author>> convertToModel(String s, ValueContext valueContext) {
                var authorsRaw = Arrays.stream(s.split(AUTHOR_DELIMITER))
                        .map(name -> {
                            var authorFromDb = authorService.findByName(name);
                            return Objects.requireNonNullElseGet(authorFromDb, () -> new Author(name));
                        })
                        .collect(Collectors.toSet());
                return Result.ok(authorsRaw);
            }

            @Override
            public String convertToPresentation(Set<Author> authors, ValueContext valueContext) {
                if (authors == null) {
                    return "";
                }
                return authors.stream().map(Author::getName).collect(Collectors.joining(AUTHOR_DELIMITER));
            }
        });
        bookGrid.getCrudFormFactory().setFieldProvider("authors", new DefaultFieldProvider(String.class));
    }

    private static void createFilterComponent(BookService bookService, GridCrud<Book> bookGrid) {
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by author name");
        filter.setClearButtonVisible(true);
        bookGrid.getCrudLayout().addFilterComponent(filter);
        filter.addValueChangeListener(e -> bookGrid.refreshGrid());
        bookGrid.setFindAllOperation(() -> bookService.findByNameContainingIgnoreCase(filter.getValue()));
    }

}
