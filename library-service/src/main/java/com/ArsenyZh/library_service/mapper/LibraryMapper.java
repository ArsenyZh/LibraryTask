package com.ArsenyZh.library_service.mapper;

import com.ArsenyZh.library_service.dto.LibraryDto;
import com.ArsenyZh.library_service.entity.Library;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryMapper {
    @Autowired
    private ModelMapper modelMapper;

    public LibraryDto convertLibraryToLibraryDto (Library library) {
        LibraryDto libraryDto = modelMapper.map(library, LibraryDto.class);

        return libraryDto;
    }

    public Library convertLibraryDtoToLibrary (LibraryDto libraryDto) {
        Library library = modelMapper.map(libraryDto, Library.class);

        return library;
    }

    public List<LibraryDto> convertLibraryListToLibraryDtoList (List<Library> libraryList) {
        List<LibraryDto> libraryDtoList = new ArrayList<>();

        for (Library library : libraryList) {
            LibraryDto libraryDto = new LibraryDto();
            libraryDto.setId(library.getId());
            libraryDto.setBookId(library.getBookId());
            libraryDto.setTimeTaken(library.getTimeTaken());
            libraryDto.setTimeDue(library.getTimeDue());

            libraryDtoList.add(libraryDto);
        }

        return libraryDtoList;
    }
}
