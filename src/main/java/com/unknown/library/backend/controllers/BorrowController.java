package com.unknown.library.backend.controllers;

import com.unknown.library.backend.controllers.dto.BorrowDTO;
import com.unknown.library.backend.models.Borrow;
import com.unknown.library.backend.models.Reader;
import com.unknown.library.backend.services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    BorrowDTO borrowBook(@RequestBody BorrowDTO borrow) {
        var borrowed = borrowService.borrow(borrow);
        return BorrowDTO.from(borrowed);
    }

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<BorrowDTO> getALL() {
        return borrowService.findAll().stream().map(BorrowDTO::from).collect(Collectors.toSet());
    }


}
