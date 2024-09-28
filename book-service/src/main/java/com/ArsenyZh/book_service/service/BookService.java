package com.ArsenyZh.book_service.service;

import com.ArsenyZh.book_service.entity.Book;
import com.ArsenyZh.book_service.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private BookRepository bookRepository;

    public List<Book> findAllBooks () {
        List<Book> bookList = bookRepository.findAll();

        return bookList;
    }

    public Book findById (Long id) {
        Book book = bookRepository.findById(id).orElse(null);

        return book;
    }

    public Book findByIsbn (String isbn) {
        Book book = bookRepository.findByIsbn(isbn);

        return book;
    }

    public Book addBook (Book book) {
        Book newBook = bookRepository.save(book);

        return newBook;
    }

    public Book updateBook (Book oldBook, Book updatedBook) {
        oldBook.setIsbn(updatedBook.getIsbn());
        oldBook.setName(updatedBook.getName());
        oldBook.setGenre(updatedBook.getGenre());
        oldBook.setAuthor(updatedBook.getAuthor());
        oldBook.setDescription(updatedBook.getDescription());
        bookRepository.save(oldBook);

        return updatedBook;
    }

    public void deleteBook (Long id) {
        bookRepository.deleteById(id);
    }
}