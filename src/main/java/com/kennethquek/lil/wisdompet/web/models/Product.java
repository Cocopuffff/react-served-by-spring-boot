package com.kennethquek.lil.wisdompet.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    public Long productId;
    public String name;
    public BigDecimal price;
    public Long vendorId;
}
