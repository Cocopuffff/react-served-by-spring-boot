package com.kennethquek.lil.wisdompet.data.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="SERVICES")
@Data
@ToString
public class ServicesEntity {
    @Id
    @Column(name="SERVICE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long serviceId;

    @Column(name="NAME", unique = true)
    public String name;

    @Column(name="PRICE", precision = 12, scale = 2)
    public BigDecimal price;
}
