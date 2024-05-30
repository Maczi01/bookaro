package com.codeart.bookaro.order.application;

import com.codeart.bookaro.catalog.db.BookJpaRepository;
import com.codeart.bookaro.order.application.port.QueryOrderUseCase;
import com.codeart.bookaro.order.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QueryOrderService implements QueryOrderUseCase {

    private final OrderRepository repository;
    private final BookJpaRepository catalogRepository;


    @Override
    public List<RichOrder> findAll() {
        return null;
    }

    @Override
    public Optional<RichOrder> findById(Long id) {
        return Optional.empty();
    }
}
