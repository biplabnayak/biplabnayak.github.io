package io.example.graphql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import io.example.graphql.model.Book;
import io.example.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

    @Autowired
    private BookRepository repository;

    @Override
    public Book get(DataFetchingEnvironment environment) {
        String id = environment.getArgument("id");
        return repository.findById(id).get();
    }
}
