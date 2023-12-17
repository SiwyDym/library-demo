package com.unknown.library.backend.controllers.dto;

import com.unknown.library.backend.models.Borrow;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
public class BorrowDTO {
    private final Long bookId;
    private final Long readerId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Nullable
    private LocalDate borrowTime;

    public static BorrowDTO from(Borrow borrowed) {
        return new BorrowDTO(
                borrowed.getBorrowingKey().getBookId(),
                borrowed.getBorrowingKey().getReaderId(),
                borrowed.getBorrowTime()
        );
    }

    public Optional<LocalDate> getBorrowTime() {
        return Optional.ofNullable(borrowTime);
    }
}
