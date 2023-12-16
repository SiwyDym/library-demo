package com.unknown.library.backend;

import com.unknown.library.backend.services.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/borrow")
public class BorrowController {

    private final BorrowService borrowService;

}
