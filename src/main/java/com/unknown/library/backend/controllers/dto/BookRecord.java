package com.unknown.library.backend.controllers.dto;

import java.time.LocalDate;

public record BookRecord(String title, LocalDate published, LocalDate borrowTime) {
}
