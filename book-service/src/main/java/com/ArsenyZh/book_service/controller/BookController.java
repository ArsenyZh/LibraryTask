package com.ArsenyZh.book_service.controller;

import com.ArsenyZh.book_service.dto.BookDto;
import com.ArsenyZh.book_service.entity.Book;
import com.ArsenyZh.book_service.feign.LibraryServiceFeignClient;
import com.ArsenyZh.book_service.mapper.BookMapper;
import com.ArsenyZh.book_service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/api/v1/books")
@RestController
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private BookMapper bookMapper;
    private LibraryServiceFeignClient feignClient;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks () {
        List<BookDto> bookDtoList = bookMapper.convertBookListToBookDtoList(bookService.findAllBooks());

        if (bookDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bookDtoList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById (@PathVariable("id") Long id) {
        if (bookService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        BookDto bookDto = bookMapper.convertBookToBookDto(bookService.findById(id));

        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn (@PathVariable("isbn") String isbn) {
        if (bookService.findByIsbn(isbn) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        BookDto bookDto = bookMapper.convertBookToBookDto(bookService.findByIsbn(isbn));

        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook (@RequestBody BookDto bookDto) {
        try {
            Book newBook = bookService.addBook(bookMapper.convertBookDtoToBook(bookDto));
            feignClient.addBookToLibrary(newBook.getId());

            return ResponseEntity.status(HttpStatus.OK).body(bookMapper.convertBookToBookDto(newBook));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook (@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        Book oldBook = bookService.findById(id);

        if (oldBook != null) {
            BookDto updatedbookDto = bookMapper.convertBookToBookDto(bookService.updateBook(oldBook, bookMapper.convertBookDtoToBook(bookDto)));

            return ResponseEntity.status(HttpStatus.OK).body(updatedbookDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBook (@PathVariable("id") Long id) {
        if (bookService.findById(id) != null) {
            bookService.deleteBook(id);

            return ResponseEntity.ok(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

