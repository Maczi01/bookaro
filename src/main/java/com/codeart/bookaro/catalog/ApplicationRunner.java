package com.codeart.bookaro.catalog;

import com.codeart.bookaro.catalog.application.CatalogController;
import com.codeart.bookaro.catalog.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final CatalogController catalogController;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationRunner(CatalogController catalogController,
                             @Value("${bookaro.catalog.query}") String title,
                             @Value("${bookaro.catalog.limit}") Long limit,
                             @Value("${bookaro.catalog.author}") String author

    ) {
        this.catalogController = catalogController;
        this.title = title;
        this.limit = limit;
        this.author = author;
    }

    @Override
    public void run(String... args) {
//        List<Book> bookList = catalogController.findByTitle(title);
//        bookList.stream()
//                .limit(limit)
//                .forEach(System.out::println);
        List<Book> bookList = catalogController.findByAuthor(author);
        bookList.stream()
                .limit(limit)
                .forEach(System.out::println);
    }

}
