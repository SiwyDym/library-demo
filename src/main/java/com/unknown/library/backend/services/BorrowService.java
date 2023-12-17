package com.unknown.library.backend.services;

import com.unknown.library.backend.controllers.dto.BorrowDTO;
import com.unknown.library.backend.models.Borrow;
import com.unknown.library.backend.models.BorrowingKey;
import com.unknown.library.backend.repositories.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
public class BorrowService {

    private final ReaderService readerService;
    private final BookService bookService;
    private final BorrowRepository borrowRepository;

    public Borrow borrow(BorrowDTO borrowDTO) {
        var reader = readerService.findById(borrowDTO.getReaderId());
        var book = bookService.findById(borrowDTO.getBookId());

        var borrow = new Borrow();
        var borrowingKey = new BorrowingKey();
        borrowingKey.setBookId(book.getId());
        borrowingKey.setReaderId(reader.getId());
        borrow.setBorrowingKey(borrowingKey);
        borrow.setReader(reader);
        borrow.setBook(book);
        borrow.setBorrowTime(borrowDTO.getBorrowTime().orElseGet(LocalDate::now));

        return borrowRepository.save(borrow);
    }

    public Collection<Borrow> findAll() {
        return borrowRepository.findAll();
    }


}
