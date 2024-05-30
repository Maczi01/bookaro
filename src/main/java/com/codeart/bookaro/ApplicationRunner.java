package com.codeart.bookaro;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.order.application.port.PlaceOrderUseCase;
import com.codeart.bookaro.order.application.port.QueryOrderUseCase;
import com.codeart.bookaro.order.domain.OrderItem;
import com.codeart.bookaro.order.domain.Recipient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final CatalogUseCase catalogService;
//    private final PlaceOrderUseCase placeOrder;
    private final QueryOrderUseCase queryOrder;
    private final String title;
    private final Long limit;
    private final String author;

    public ApplicationRunner(CatalogUseCase catalogService,
//                             PlaceOrderUseCase placeOrder,
                             QueryOrderUseCase queryOrder,
                             @Value("${bookaro.catalog.query}") String title,
                             @Value("${bookaro.catalog.limit}") Long limit,
                             @Value("${bookaro.catalog.author}") String author

    ) {
        this.catalogService = catalogService;
//        this.placeOrder = placeOrder;
        this.queryOrder = queryOrder;
        this.title = title;
        this.limit = limit;
        this.author = author;
    }

    @Override
    public void run(String... args) {
        initData();
        searchCatalog();
        placeOrder();
    }

    private void placeOrder() {
        Book pan = catalogService.findOneByTitle("pan").orElseThrow(() -> new IllegalArgumentException("Cannot find a book"));
//        Book harry = catalogService.findOneByTitle("har").orElseThrow(() -> new IllegalArgumentException("Cannot find a book"));

        Recipient recipient = Recipient.builder()
                .name("Recypient")
                .city("Warszawa")
                .email("recypient@rec.com")
                .phone("0700")
                .zipCode("56-271")
                .street("Blotna")
                .build();
        PlaceOrderUseCase.PlaceOrderCommand command = PlaceOrderUseCase.PlaceOrderCommand
                .builder()
                .recipient(recipient)
                .item(new OrderItem(pan, 16))
//                .item(new OrderItem(harry, 7))
                .build();
//        PlaceOrderUseCase.PlaceOrderResponse response = placeOrder.placeOrder(command);
//        System.out.println("Created ORDER with ID " + response.getOrderId() );
//        queryOrder.findAll().stream().forEach(f -> System.out.println("total price: " + f.totalPrice()));

    }

    private void searchCatalog() {
//        findAndUpdate();
        List<Book> all = catalogService.findAll();
        all.stream().forEach(System.out::println);
    }

    private void findAndUpdate() {
        catalogService.findOneByTitleAndAuthor("Harr", "row")
                .ifPresent(book -> {
                    CatalogUseCase.UpdateBookCommand updatedCommandBook = CatalogUseCase.UpdateBookCommand
                            .builder()
                            .id(book.getId())
                            .title("Ostatni Zajazd na Litwie")
                            .build();;
                    CatalogUseCase.UpdateBookResponse updateBookResponse = catalogService.updateBook(updatedCommandBook);
                    System.out.println("Update result: " + updateBookResponse.isSuccess());
                });
    }

    private void initData() {
        catalogService.addBook(new CatalogUseCase.CreateBookCommand("Harry Potter", "Rowling", 1999, new BigDecimal("19")));
        catalogService.addBook(new CatalogUseCase.CreateBookCommand("pan tadeusz", "Mickiewicz", 1789, new BigDecimal("15")));
        catalogService.addBook(new CatalogUseCase.CreateBookCommand("pan wolodyjowski", "Sien", 1789, new BigDecimal("12")));
        catalogService.addBook(new CatalogUseCase.CreateBookCommand("pan tarej", "Remont", 1789, new BigDecimal("10")));

//        bookMap.put(1L, new Book(1L, "pan tadeusz", "Mickiewicz", 1789));
//        bookMap.put(2L, new Book(2L, "pan wolodyjowski", "Sien", 1789));
//        bookMap.put(3L, new Book(3L, "pan tarej", "Mickiewicz", 1789));
    }
}
//    public void findBook(String... args) {
////        List<Book> bookList = catalogService.findByTitle(title);
////        bookList.stream()
////                .limit(limit)
////                .forEach(System.out::println);
//        List<Book> bookList = catalogService.findByAuthor(author);
//        bookList.stream()
//                .limit(limit)
//                .forEach(System.out::println);
//    }
