package com.codeart.bookaro.catalog.infrastructure;

import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.catalog.domain.CatalogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryCatalogRepository implements CatalogRepository {

    private final Map<Long, Book> bookMap = new ConcurrentHashMap<>();
    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookMap.values()
                .stream()
                .filter(book -> book.getAuthor().toLowerCase().startsWith(author))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Book save(Book book) {
        if (book.getId() != null) {
            bookMap.put(book.getId(), book);
            return book;
        } else {
            long newId = nextId();
            Book newBook = new Book(newId, book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice());
            bookMap.put(newId, newBook);
            return newBook;
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookMap.get(id));
    }

    @Override
    public void removeById(Long id) {
        bookMap.remove(id);
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }

}
