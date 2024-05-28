package com.codeart.bookaro.order.application.port;

import com.codeart.bookaro.order.domain.Order;

import java.util.List;

public interface QueryOrderUseCase {
    List<Order> findAll();
}
