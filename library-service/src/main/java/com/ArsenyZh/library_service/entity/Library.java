package com.ArsenyZh.library_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "books_id")
    private Long bookId;

    @Column(name = "time_taken")
    private LocalDate timeTaken;

    @Column(name = "time_due")
    private LocalDate timeDue;
}