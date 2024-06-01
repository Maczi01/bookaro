package com.codeart.bookaro.catalog.application.port;

import com.codeart.bookaro.catalog.domain.Author;

import java.util.List;

public interface AuthorsUseCase {

    List<Author> findAll();
}
