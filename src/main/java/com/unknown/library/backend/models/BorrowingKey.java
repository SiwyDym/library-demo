package com.unknown.library.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
public class BorrowingKey implements Serializable {
    @Column(name = "book_id")
    Long bookId;

    @Column(name = "reader_id")
    Long readerId;
}
