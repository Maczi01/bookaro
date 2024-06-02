package com.codeart.bookaro.catalog.application.port;

import com.codeart.bookaro.catalog.domain.Author;
import com.codeart.bookaro.catalog.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptyList;

public interface CatalogUseCase {
    List<Book> findByTitle(String title);

    List<Book> findAll();

    Book addBook(CreateBookCommand command);

    List<Book> findOneByAuthor(String author);

    void removeById(Long id);

    UpdateBookResponse updateBook(UpdateBookCommand command);

    Optional<Book> findOneByTitle(String title);

    Optional<Book> findById(Long id);

    void updateBookCover(UpdateBookCoverCommand cover);

    void removeBookCover(Long id);

//    @Value
//    class UpdateBookCoverCommand {
//        Long id;
//        byte[] file;
//        String contentType;
//        String fileName;
//    }

//    @Value
//    class CreateBookCommand {
//        String title;
//        Set<Long> authors;
//        Integer year;
//        BigDecimal price;
//    }

//    @Value
//    @Builder
//    @AllArgsConstructor
//    class UpdateBookCommand {
//        Long id;
//        String title;
//        Set<Author> authors;
//        Integer year;
//        BigDecimal price;
//
//        public Book updateFields(Book book) {
//            if (title != null) {
//                book.setTitle(title);
//            }
////            if (author != null) {
////                book.setAuthor(author);
////            }
//            if (year != null) {
//                book.setYear(year);
//            }
//            if (price != null) {
//                book.setPrice(price);
//            }
//            return book;
//        }
//    }




    @Value
    class UpdateBookCoverCommand {
        Long id;
        byte[] file;
        String contentType;
        String filename;
    }

    @Value
    class CreateBookCommand {
        String title;
        Set<Long> authors;
        Integer year;
        BigDecimal price;
    }

    @Value
    @Builder
    @AllArgsConstructor
    class UpdateBookCommand {
        Long id;
        String title;
        Set<Long> authors;
        Integer year;
        BigDecimal price;
    }

    @Value
    class UpdateBookResponse {
        public static UpdateBookResponse SUCCESS = new UpdateBookResponse(true, emptyList());

        boolean success;
        List<String> errors;
    }
}
