package com.ArsenyZh.library_service.repository;

import com.ArsenyZh.library_service.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    Library findLibraryByBookId(Long bookId);

    @Query("SELECT l FROM Library l WHERE l.timeDue IS NULL")
    List<Library> findAllByTimeDueIsNull();
}