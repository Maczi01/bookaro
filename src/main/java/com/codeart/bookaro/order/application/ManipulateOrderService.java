package com.codeart.bookaro.order.application;

import com.codeart.bookaro.order.application.port.ManipulateOrderUseCase;
import com.codeart.bookaro.order.domain.Order;
import com.codeart.bookaro.order.domain.OrderRepository;
import com.codeart.bookaro.order.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManipulateOrderService implements ManipulateOrderUseCase {

    private final OrderRepository repository;

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderCommand command) {
        Order order = Order
                .builder()
                .recipient(command.getRecipient())
                .items(command.getItems())
                .build();
        Order savedOrder = repository.save(order);
        System.out.println("saved" + savedOrder);
        return PlaceOrderResponse.success(savedOrder.getId());
    }

    @Override
    public void deleteOrderById(Long id) {
        repository.deleteById(id);

    }

    @Override
    public void updateOrderStatus(Long id, OrderStatus status) {
        repository.findById(id)
                .ifPresent(order -> {
                    order.setOrderStatus(status);
                    repository.save(order);
                });
    }
}
