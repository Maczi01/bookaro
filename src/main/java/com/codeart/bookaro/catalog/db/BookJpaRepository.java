package com.codeart.bookaro.catalog.db;

import com.codeart.bookaro.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthors_firstNameContainsIgnoreCase(String name);

}
