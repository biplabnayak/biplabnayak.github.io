package io.example.graphql.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Book {
    @Id
    private String bookId;
    private String bookName;
    private String publishedDate;
    private String[] writer;
    private String publisher;
}
