package com.unknown.library.backend.controllers;

import com.unknown.library.backend.controllers.dto.ReaderDTO;
import com.unknown.library.backend.controllers.dto.ReaderResponseRecord;
import com.unknown.library.backend.models.Reader;
import com.unknown.library.backend.services.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Reader borrowBook(@RequestBody ReaderDTO reader) {
        return readerService.saveBrandNew(reader);
    }

    @GetMapping(value = "/violates", produces = MediaType.APPLICATION_JSON_VALUE)
    Collection<ReaderResponseRecord> getAllThatViolateReturningTime() {
        return readerService.findAllThatViolateTimeReturning();
    }
}
