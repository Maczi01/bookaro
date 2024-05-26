package com.codeart.bookaro.catalog.application;

import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.catalog.domain.CatalogService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CatalogController {
    private final CatalogService catalogService;

    CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    List<Book> findByTitle(String title){
        return catalogService.findByTitle(title);
    }

    public List<Book> findByAuthor(String author){
        return catalogService.findByAuthor(author);
    }


}
