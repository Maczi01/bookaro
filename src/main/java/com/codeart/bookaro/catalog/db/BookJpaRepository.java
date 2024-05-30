package com.codeart.bookaro.catalog.db;

import com.codeart.bookaro.catalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<Book, Long> {


}
