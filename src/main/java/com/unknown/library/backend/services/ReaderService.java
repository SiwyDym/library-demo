package com.unknown.library.backend.services;

import com.unknown.library.backend.controllers.dto.BookRecord;
import com.unknown.library.backend.controllers.dto.ReaderDTO;
import com.unknown.library.backend.controllers.dto.ReaderResponseRecord;
import com.unknown.library.backend.models.Reader;
import com.unknown.library.backend.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository repository;

    public Reader findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Reader of given id[" + id + "] doesn't exist."));
    }

    public Reader saveBrandNew(ReaderDTO readerDTO) {
        var reader = new Reader();
        reader.setName(readerDTO.name());

        return repository.save(reader);
    }

    public Collection<ReaderResponseRecord> findAllThatViolateTimeReturning() {

        LocalDate currentDateMinus30days = LocalDate.now().minusDays(30);
        var temp = repository.findAll(ReaderRepository.violatesReturningTime(currentDateMinus30days));
        return temp.stream()
                .map(r -> new ReaderResponseRecord(r.getId(), r.getName(),
                                r.getBorrowBooks().stream()
                                        .filter(b -> b.getBorrowTime().isBefore(currentDateMinus30days)) //fix: change violatesReturningTime
                                        .map(b -> {
                                            var book = b.getBook();
                                            return new BookRecord(book.getTitle(), book.getPublished(), b.getBorrowTime());
                                        })
                                        .collect(Collectors.toSet())
                        )
                )
                .collect(Collectors.toSet());
    }
}
