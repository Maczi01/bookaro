package com.codeart.bookaro.catalog.application;

import com.codeart.bookaro.catalog.application.port.AuthorsUseCase;
import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.db.AuthorJpaRepository;
import com.codeart.bookaro.catalog.db.BookJpaRepository;
import com.codeart.bookaro.catalog.domain.Author;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.uploads.application.port.UploadUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class AuthorService implements AuthorsUseCase {

    private final AuthorJpaRepository repository;

    @Override
    public List<Author> findAll() {
        return repository.findAll();
    }

}

