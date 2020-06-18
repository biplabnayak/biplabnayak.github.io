package io.example.graphql.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.example.graphql.model.Book;
import io.example.graphql.repository.BookRepository;
import io.example.graphql.service.datafetcher.AllBookDataFetcher;
import io.example.graphql.service.datafetcher.BookDataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GraphqlService {

    @Value("classpath:books.schema")
    private Resource schemaResource;

    @Autowired
    private AllBookDataFetcher allBookDataFetcher;

    @Autowired
    private BookDataFetcher bookDataFetcher;

    @Autowired
    private BookRepository repository;

    private GraphQL graphQL;


    @PostConstruct
    public void loadSchema() throws IOException {
        // get the schema
        File schemaFile = schemaResource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    @PostConstruct
    public void initBooks() {
        List<Book> books = new ArrayList< >();
        books.add(new Book("101", "The Science of Marvel",
                "22-12-2017", new String[] {
                "Sebastian"
        },
                "Infinity Stones"));
        books.add(new Book("102", "The Sixth Extinction",
                "22-12-2017", new String[] {
                "Sebastian",
                "Elizabeth"
        },
                "Infinity Stones"));
        books.add(new Book("103", "The Science of Marvel -2",
                "22-12-2019", new String[] {
                "Sebastian"
        },
                "Infinity Stones"));
        repository.saveAll(books);
    }


    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                .dataFetcher("allBooks", allBookDataFetcher).dataFetcher("Book", bookDataFetcher))
                .build();
    }

    public Object execute(String query) {
        return graphQL.execute(query);
    }

}
