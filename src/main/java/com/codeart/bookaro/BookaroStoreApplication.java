package com.codeart.bookaro;

import com.codeart.bookaro.catalog.application.CatalogController;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.catalog.domain.CatalogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class BookaroStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookaroStoreApplication.class, args);
	}

	private final CatalogController catalogController;

	public BookaroStoreApplication(CatalogController catalogController) {
		this.catalogController = catalogController;
	}


}
