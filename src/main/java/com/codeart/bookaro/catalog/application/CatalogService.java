package com.codeart.bookaro.catalog.application;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.catalog.domain.CatalogRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class CatalogService implements CatalogUseCase {

    private final CatalogRepository repository;

    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().startsWith(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Book> findOneByTitleAndAuthor(String title, String author) {
        return findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().startsWith(title) || book.getAuthor().toLowerCase().startsWith(author))
                .findAny();
    }

    @Override
    public void addBook(CreateBookCommand command) {
        Book book = command.toBook();
        repository.save(book);
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }

    @Override
    public UpdateBookResponse updateBook(UpdateBookCommand command) {
        return repository
                .findById(command.getId())
                .map(book -> {
                    command.updateFields(book);
                    repository.save(book);
                    return UpdateBookResponse.SUCCESS;
                })
                .orElseGet(() -> new UpdateBookResponse(false, Arrays.asList("Book with ID: " + command.getId() + " not found")));
    }


    @Override
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    @Override
    public Optional<Book> findOneByTitle(String title) {
        return findAll()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().startsWith(title))
                .findFirst();
    }

    @Override
    public Optional<Book> findById(Long id) {
        
        return repository.findById(id);
    }
}

