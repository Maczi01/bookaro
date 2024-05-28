package com.codeart.bookaro.catalog.web;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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




}
