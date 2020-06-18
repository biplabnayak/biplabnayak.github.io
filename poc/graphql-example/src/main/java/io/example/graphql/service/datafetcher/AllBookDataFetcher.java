package io.example.graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.example.graphql.model.Book;
import io.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class AllBookDataFetcher implements DataFetcher<List<Book>> {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
