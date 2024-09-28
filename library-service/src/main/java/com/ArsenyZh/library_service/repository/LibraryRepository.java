package com.ArsenyZh.library_service.repository;

import com.ArsenyZh.library_service.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findLibraryByBookId(Long bookId);
}