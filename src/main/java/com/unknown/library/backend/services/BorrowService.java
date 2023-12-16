package com.unknown.library.backend.services;

import com.unknown.library.backend.models.Borrow;
import com.unknown.library.backend.repositories.BookRepository;
import com.unknown.library.backend.repositories.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;


    public Borrow borrow(String bookId, String readerId){

        return borrowRepository.save(new Borrow());
    }
}
