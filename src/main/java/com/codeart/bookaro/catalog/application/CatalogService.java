package com.codeart.bookaro.catalog.application;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.db.BookJpaRepository;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.uploads.application.port.UploadUseCase;
import com.codeart.bookaro.uploads.domain.Upload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.codeart.bookaro.uploads.application.port.UploadUseCase.SaveUploadCommand;

@Service
@AllArgsConstructor
class CatalogService implements CatalogUseCase {

    private final BookJpaRepository repository;
    private final UploadUseCase upload;

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
    public Book addBook(CreateBookCommand command) {
        Book book = command.toBook();
        return repository.save(book);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
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


//    @Override
//    public List<Book> findByAuthor(String author) {
//        return repository.findByAuthor(author);
//    }

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

    @Override
    public void updateBookCover(UpdateBookCoverCommand command) {
        repository.findById(command.getId())
                .ifPresent(book -> {
                    Upload savedUpload = upload.save(new SaveUploadCommand(command.getFileName(), command.getFile(), command.getContentType()));
                    book.setCoverId(savedUpload.getId());
                    repository.save(book);
                });
    }

    @Override
    public void removeBookCover(Long id) {
        repository.findById(id).ifPresent(book -> {
            if (book.getCoverId() != null) {
                upload.removeById(book.getCoverId());
                book.setCoverId(null);
                repository.save(book);
            }
        });
    }
}

