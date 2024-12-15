package com.kennethquek.lil.wisdompet.data.entities;

import com.kennethquek.lil.wisdompet.web.models.Vendor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="PRODUCTS")
@Data
@ToString
public class ProductEntity {
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME", unique = true)
    private String name;

    @Column(name="PRICE", precision = 12, scale = 2)
    private BigDecimal price;

    @JoinColumn(name="VENDOR_ID")
    private Long vendorId;

}
