package com.codeart.bookaro.catalog.web;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogUseCase catalogService;

    @GetMapping()
    public List<Book> getAll(
            @RequestParam Optional<String> title,
            @RequestParam Optional<String> author
    ) {
        if (title.isPresent() && author.isEmpty()) {
            return catalogService.findByTitle(title.get());
        }
//        else if (title.isEmpty() && author.isPresent()) {
//            return catalogService.findByAuthor(author.get());
//        }
        return catalogService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return catalogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"title"})
    public List<Book> findByTitle(@RequestParam(required = false) String title) {
        return catalogService.findByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addBook(@Valid @RequestBody CatalogController.RestBookCommand command) {
        Book book = catalogService.addBook(command.toCreateCommand());
        System.out.println("book----->" + book);
        URI uri = getUri(book);
        return ResponseEntity.created(uri).build();
//        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateBook(@PathVariable Long id, @Valid @RequestBody RestBookCommand command) {
        catalogService.updateBook(command.toUpdateCommand(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        catalogService.removeById(id);
    }

    @PutMapping(value = "/{id}/cover", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addBookCover(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        catalogService.updateBookCover(new CatalogUseCase.UpdateBookCoverCommand(id,
                file.getBytes(),
                file.getContentType(),
                file.getName()));
    }

    @DeleteMapping("/{id}/cover")
    public void removeBookCover(@PathVariable Long id){
        catalogService.removeBookCover(id);
    }

    private URI getUri(Book book) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + book.getId().toString()).build().toUri();
    }

    @Data
    private static class RestBookCommand {
        @NotBlank(message = "Please provide a title")
        private String title;

        @NotEmpty
        private Set<Long> authors;

        @NotNull
        private Integer year;

        @NotNull
        @DecimalMin("0.00")
        private BigDecimal price;

        CatalogUseCase.CreateBookCommand toCreateCommand() {
            return new CatalogUseCase.CreateBookCommand(title, authors, year, price);
        }

        CatalogUseCase.UpdateBookCommand toUpdateCommand(Long id) {
            return new CatalogUseCase.UpdateBookCommand(id, title, authors, year, price);
        }
    }


}
