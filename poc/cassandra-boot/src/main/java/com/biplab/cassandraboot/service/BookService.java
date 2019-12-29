package com.biplab.cassandraboot.service;

import com.biplab.cassandraboot.model.Book;
import com.biplab.cassandraboot.repository.BookRepository;
import com.datastax.driver.core.utils.UUIDs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @PostConstruct
    public void initialize() {

        Book book = new Book();
        book.setId(UUIDs.timeBased());
        book.setTitle("Test");
        book.setPublisher("Test");

        log.info("Saving sample book : {}", book);

        bookRepository.save(book);
    }
}
