package com.ArsenyZh.library_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LibraryDto {
    private Long bookId;
    private LocalDate timeTaken;
    private LocalDate timeDue;
}
