package com.unknown.library.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Borrow {
    @EmbeddedId
    private BorrowingKey borrowingKey;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("readerId")
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate borrowTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate returnedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Borrow borrow)) return false;
        return Objects.equals(borrowingKey, borrow.borrowingKey)
                && Objects.equals(book, borrow.book)
                && Objects.equals(reader, borrow.reader)
                && Objects.equals(borrowTime, borrow.borrowTime
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingKey, book, reader, borrowTime);
    }
}
