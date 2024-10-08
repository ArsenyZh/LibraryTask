package com.ArsenyZh.book_service.repository;

import com.ArsenyZh.book_service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
}
