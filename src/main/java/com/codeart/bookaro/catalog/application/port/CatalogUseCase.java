package com.codeart.bookaro.catalog.application.port;

import com.codeart.bookaro.catalog.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public interface CatalogUseCase {
    List<Book> findByTitle(String title);

    List<Book> findAll();

    Optional<Book> findOneByTitleAndAuthor(String title, String author);

    Book addBook(CreateBookCommand command);

    void removeById(Long id);

    UpdateBookResponse updateBook(UpdateBookCommand command);

//    List<Book> findByAuthor(String author);

    Optional<Book> findOneByTitle(String title);

    Optional<Book> findById(Long id);

    void updateBookCover(UpdateBookCoverCommand cover);

    void removeBookCover(Long id);

    @Value
    class UpdateBookCoverCommand {
        Long id;
        byte[] file;
        String contentType;
        String fileName;
    }

    @Value
    class CreateBookCommand {
        String title;
        String author;
        Integer year;
        BigDecimal price;

        public Book toBook() {
            return new Book(title, author, year, price);
        }
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateBookCommand {
        Long id;
        String title;
        String author;
        Integer year;
        BigDecimal price;

        public Book updateFields(Book book) {
            if (title != null) {
                book.setTitle(title);
            }
            if (author != null) {
                book.setAuthor(author);
            }
            if (year != null) {
                book.setYear(year);
            }
            if (price != null) {
                book.setPrice(price);
            }
            return book;
        }
    }


    @Value
    class UpdateBookResponse {
        public static UpdateBookResponse SUCCESS = new UpdateBookResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}
