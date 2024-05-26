package com.codeart.bookaro.catalog.infrastructure;

import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.catalog.domain.CatalogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class MemoryCatalogRepository implements CatalogRepository {

    private final Map<Long, Book> bookMap = new ConcurrentHashMap<>();

    public MemoryCatalogRepository() {
        bookMap.put(1L, new Book(1L, "pan tadeusz", "Mickiewicz", 1789));
        bookMap.put(2L, new Book(2L, "pan wolodyjowski", "Sien", 1789));
        bookMap.put(3L, new Book(3L, "pan tarej", "Mickiewicz", 1789));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookMap.values()
                .stream()
                .filter(book -> book.author().toLowerCase().startsWith(author))
                .collect(Collectors.toList());

    }

}
