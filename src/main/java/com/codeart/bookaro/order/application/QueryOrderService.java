package com.codeart.bookaro.order.application;

import com.codeart.bookaro.order.application.port.QueryOrderUseCase;
import com.codeart.bookaro.order.domain.Order;
import com.codeart.bookaro.order.domain.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryOrderService implements QueryOrderUseCase {

    private OrderRepository repository;

    public QueryOrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }
}
