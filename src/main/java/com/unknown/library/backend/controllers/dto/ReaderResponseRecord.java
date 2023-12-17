package com.unknown.library.backend.controllers.dto;

import java.util.Collection;

public record ReaderResponseRecord(Long id, String name, Collection<BookRecord> books) {
}


