package com.codeart.bookaro.catalog.web;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        if (title.isPresent() && author.isEmpty()){
            return catalogService.findByTitle(title.get());
        }
        else if (title.isEmpty() && author.isPresent()){
            return catalogService.findByAuthor(author.get());
        }
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
    public ResponseEntity<Void> addBook(@RequestBody RestCreateBookCommand command){
        Book book = catalogService.addBook(command.toCommand());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + book.getId().toString()).build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @Data
    private static class RestCreateBookCommand {
            private String title;
            private String author;
            private Integer year;
            private BigDecimal price;

            CatalogUseCase.CreateBookCommand toCommand(){
                return new CatalogUseCase.CreateBookCommand(title, author, year, price);
            }
    }


}
