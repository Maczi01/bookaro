package com.codeart.bookaro.catalog.domain;

import java.util.List;

public interface CatalogRepository {
    List<Book> findAll();

    List<Book> findByAuthor(String author);
}
