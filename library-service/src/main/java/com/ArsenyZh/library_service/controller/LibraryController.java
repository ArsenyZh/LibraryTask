package com.ArsenyZh.library_service.controller;

import com.ArsenyZh.library_service.dto.LibraryDto;
import com.ArsenyZh.library_service.mapper.LibraryMapper;
import com.ArsenyZh.library_service.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/library")
@RestController
@AllArgsConstructor
public class LibraryController {
    private LibraryService libraryService;
    private LibraryMapper libraryMapper;

    @GetMapping("/")
    public List<LibraryDto> getAllLibrary () {
        List<LibraryDto> libraryDtoList = libraryMapper.convertLibraryListToLibraryDtoList(libraryService.findAllLibraries());

        return libraryDtoList;
    }

    @PostMapping("/add_book")
    public void addBookToLibrary (@RequestBody Long bookId) {
        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.saveBookToLibrary(bookId));
        System.out.println(libraryDto);
    }

    @PostMapping("/take_book/{id}")
    public LibraryDto takeBookFromLibrary (@PathVariable("id") Long id) {
        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.takeBookFromLibrary(id));

        return libraryDto;
    }

    @PostMapping("/return_book/{id}")
    public LibraryDto returnBookToLibrary (@PathVariable("id") Long id) {
        LibraryDto libraryDto = libraryMapper.convertLibraryToLibraryDto(libraryService.returnBookToLibrary(id));

        return libraryDto;
    }
}