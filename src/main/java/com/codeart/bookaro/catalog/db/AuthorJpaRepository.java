package com.codeart.bookaro.catalog.db;

import com.codeart.bookaro.catalog.domain.Author;
import com.codeart.bookaro.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorJpaRepository extends JpaRepository<Author, Long> {


}
