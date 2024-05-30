package com.codeart.bookaro.order.db;

import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
