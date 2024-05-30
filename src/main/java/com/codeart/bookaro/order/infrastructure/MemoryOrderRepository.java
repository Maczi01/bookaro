package com.codeart.bookaro.order.infrastructure;

import com.codeart.bookaro.catalog.domain.Book;
import com.codeart.bookaro.order.domain.Order;
import com.codeart.bookaro.order.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> storage = new ConcurrentHashMap<>();
    private final AtomicLong ID_NEXT_VALUE = new AtomicLong(0L);


    @Override
    public Order save(Order order) {
        if(order.getId() != null){
            storage.put(order.getId(), order);
        } else {
            long nextId = nextId();
            order.setId(nextId);
            order.setCreatedAt(LocalDateTime.now());
            storage.put(nextId, order);
        }
        return order;

    }

    @Override
    public List<Order> findAll() {
        return storage.values().stream().collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    private long nextId() {
        return ID_NEXT_VALUE.getAndIncrement();
    }

}
