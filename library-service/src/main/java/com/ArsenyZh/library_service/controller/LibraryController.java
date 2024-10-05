package com.ArsenyZh.library_service.controller;

import com.ArsenyZh.library_service.dto.LibraryDto;
import com.ArsenyZh.library_service.mapper.LibraryMapper;
import com.ArsenyZh.library_service.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

//@RequestMapping("/api/v1/library")
@RestController
@AllArgsConstructor
public class LibraryController {
    private LibraryService libraryService;
    private LibraryMapper libraryMapper;

    @GetMapping("/")
    public ResponseEntity<List<LibraryDto>> getAllLibrary () {
        List<LibraryDto> libraryDtoList = libraryMapper.convertLibraryListToLibraryDtoList(libraryService.findAllLibraries());

        if (libraryDtoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libraryDtoList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(libraryDtoList);
    }

    @GetMapping("/free_books")
    public ResponseEntity<List<LibraryDto>> getAllFreeBooksFromLibrary() {
        List<LibraryDto> freeBooksList = libraryMapper.convertLibraryListToLibraryDtoList(libraryService.freeBooks());

        if (freeBooksList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(freeBooksList);
        }
        return ResponseEntity.status(HttpStatus.OK).body(freeBooksList);
    }

    @PostMapping("/add_book")
    public void addBookToLibrary (@RequestBody Long bookId) {
        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.saveBookToLibrary(bookId));
    }

    @PostMapping("/take_book/{id}")
    public ResponseEntity<LibraryDto> takeBookFromLibrary (@PathVariable("id") Long id) {
        if (libraryService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.takeBookFromLibrary(id));

        return ResponseEntity.status(HttpStatus.OK).body(libraryDto);
    }

    @PostMapping("/return_book/{id}")
    public ResponseEntity<LibraryDto> returnBookToLibrary (@PathVariable("id") Long id) {
        if (libraryService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.returnBookToLibrary(id));

        return ResponseEntity.status(HttpStatus.OK).body(libraryDto);
    }
}