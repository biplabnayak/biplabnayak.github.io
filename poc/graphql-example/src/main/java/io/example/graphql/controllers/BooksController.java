package io.example.graphql.controllers;

import io.example.graphql.service.GraphqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    GraphqlService graphqlService;

    @PostMapping
    public ResponseEntity<Object> books(@RequestBody String query) {
        return ResponseEntity.ok(graphqlService.execute(query));
    }
}
