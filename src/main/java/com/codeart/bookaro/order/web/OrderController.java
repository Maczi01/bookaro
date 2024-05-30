package com.codeart.bookaro.order.web;

import com.codeart.bookaro.catalog.application.port.CatalogUseCase;
import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.order.application.port.ManipulateOrderUseCase;
import com.codeart.bookaro.order.application.port.QueryOrderUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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

import static com.codeart.bookaro.order.application.port.QueryOrderUseCase.*;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

//    private final ManipulateOrderUseCase manipulateOrder;
//    private final QueryOrderUseCase queryOrder;

//    @GetMapping
//    public List<RichOrder> getOrders() {
//        return queryOrder.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RichOrder> getOrderById(@PathVariable Long id) {
//        return queryOrder.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

}
