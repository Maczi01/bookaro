package com.codeart.bookaro.order.domain;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    List<Order> findAll();

    void deleteById(Long id);

    Optional<Order> findById(Long id);
}
