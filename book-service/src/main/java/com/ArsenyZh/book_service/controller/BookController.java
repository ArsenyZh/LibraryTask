package com.ArsenyZh.book_service.controller;

import com.ArsenyZh.book_service.dto.BookDto;
import com.ArsenyZh.book_service.entity.Book;
import com.ArsenyZh.book_service.feign.LibraryServiceFeignClient;
import com.ArsenyZh.book_service.mapper.BookMapper;
import com.ArsenyZh.book_service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/books")
@RestController
@AllArgsConstructor
public class BookController {
    private BookService bookService;
    private BookMapper bookMapper;
    private LibraryServiceFeignClient feignClient;

    @GetMapping("/")
    public List<BookDto> getAllBooks () {
        List<BookDto> bookDtoList = bookMapper.convertBookListToBookDtoList(bookService.findAllBooks());

        return bookDtoList;
    }

    @GetMapping("/{id}")
    public BookDto getBookById (@PathVariable("id") Long id) {
        BookDto bookDto = bookMapper.convertBookToBookDto(bookService.findById(id));

        return bookDto;
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getBookByIsbn (@PathVariable("isbn") String isbn) {
        BookDto bookDto = bookMapper.convertBookToBookDto(bookService.findByIsbn(isbn));

        return bookDto;
    }

    @PostMapping("/add")
    public BookDto addBook (@RequestBody BookDto bookDto) {
        Book newBook = bookService.addBook(bookMapper.convertBookDtoToBook(bookDto));
        feignClient.addBookToLibrary(newBook.getId());

        return bookMapper.convertBookToBookDto(newBook);
    }

    @PutMapping("/update/{id}")
    public BookDto updateBook (@PathVariable("id") Long id, @RequestBody BookDto bookDto) {
        Book oldBook = bookService.findById(id);

        if (oldBook != null) {
            BookDto updatedbookDto = bookMapper.convertBookToBookDto(bookService.updateBook(oldBook, bookMapper.convertBookDtoToBook(bookDto)));

            return updatedbookDto;
        } else {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook (@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}

