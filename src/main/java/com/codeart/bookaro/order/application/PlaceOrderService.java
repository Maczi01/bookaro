package com.codeart.bookaro.order.application;

import com.codeart.bookaro.order.application.port.PlaceOrderUseCase;
import com.codeart.bookaro.order.application.port.QueryOrderUseCase;
import com.codeart.bookaro.order.domain.Order;
import com.codeart.bookaro.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceOrderService implements PlaceOrderUseCase {

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
}
