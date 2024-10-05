package com.ArsenyZh.book_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "library-service", url = "http://library-service:8082/")
public interface LibraryServiceFeignClient {

    @PostMapping("/add_book")
    void addBookToLibrary (Long bookId);
}
