package com.ArsenyZh.library_service.service;

import com.ArsenyZh.library_service.entity.Library;
import com.ArsenyZh.library_service.repository.LibraryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class LibraryService {
    private LibraryRepository libraryRepository;

    public Library findById (Long id) {
        Library library = libraryRepository.findById(id).orElse(null);

        return library;
    }

    public List<Library> findAllLibraries () {
        List<Library> libraryList = libraryRepository.findAll();

        return libraryList;
    }

    public Library saveBookToLibrary (Long bookId) {
        Library library = new Library();
        library.setBookId(bookId);
        libraryRepository.save(library);
        return library;
    }

    public Library takeBookFromLibrary(Long id) {
        Library library = libraryRepository.findById(id).orElse(null);
        System.out.println(library.toString());

        if (library != null && library.getTimeDue() == null) {
            LocalDate currentDate = LocalDate.now();
            LocalDate deadlineDate = currentDate.plusMonths(1);

            library.setTimeTaken(currentDate);
            library.setTimeDue(deadlineDate);
            libraryRepository.save(library);

            return library;
        } else {
            return null;
        }
    }

    public Library returnBookToLibrary (Long id) {
        Library library = libraryRepository.findById(id).orElse(null);

        if (library != null) {
            library.setTimeTaken(null);
            library.setTimeDue(null);
            libraryRepository.save(library);

            return library;
        } else {
            return null;
        }
    }

    public List<Long> freeBooks () {
        List<Long> freeBooksIdList = libraryRepository.findAllByTimeDueIsNotNull();

        return freeBooksIdList;
    }
}
