package com.codeart.bookaro;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookaroStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookaroStoreApplication.class, args);
	}

	private final CatalogUseCase catalogController;

	public BookaroStoreApplication(CatalogUseCase catalogController) {
		this.catalogController = catalogController;
	}


}
