package com.kennethquek.lil.wisdompet.web.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductWithVendorName {
    public Long productId;
    public String name;
    public BigDecimal price;
    public Long vendorId;
    public String vendorName;

    public ProductWithVendorName(Product product, String vendorName) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.vendorId = product.getVendorId();
        this.vendorName = vendorName;
    }
}
