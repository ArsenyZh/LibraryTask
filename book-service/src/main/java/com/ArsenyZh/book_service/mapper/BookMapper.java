package com.ArsenyZh.book_service.mapper;

import com.ArsenyZh.book_service.dto.BookDto;
import com.ArsenyZh.book_service.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BookDto convertBookToBookDto (Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);

        return bookDto;
    }

    public Book convertBookDtoToBook (BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);

        return book;
    }

    public List<BookDto> convertBookListToBookDtoList (List<Book> bookList) {
        List<BookDto> bookDtoList = new ArrayList<BookDto>();

        for (Book book : bookList) {
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setIsbn(book.getIsbn());
            bookDto.setName(book.getName());
            bookDto.setGenre(book.getGenre());
            bookDto.setDescription(book.getDescription());
            bookDto.setAuthor(book.getAuthor());

            bookDtoList.add(bookDto);
        }

        return bookDtoList;
    }
}
