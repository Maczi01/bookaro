package com.codeart.bookaro.order.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Recipient {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String zipCode;
    private String email;
}
