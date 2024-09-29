package com.ArsenyZh.book_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "library-service", url = "http://localhost:8082/api/v1/library/")
public interface LibraryServiceFeignClient {

    @PostMapping("/add_book")
    void addBookToLibrary (Long bookId);
}
