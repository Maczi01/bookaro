package com.codeart.bookaro.catalog.web;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogUseCase catalogService;

    @GetMapping()
    public List<Book> findAll() {
        return catalogService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return catalogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
