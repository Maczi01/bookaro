package com.codeart.bookaro.order.domain;

import com.codeart.bookaro.catalog.domain.Book;
import lombok.Value;

@Value
public class OrderItem {
    Book book;
    int quantity;
}
