package com.codeart.bookaro.order.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Recipient {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String email;
}
