package com.kennethquek.lil.wisdompet.data.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="VENDORS")
@Data
@ToString
public class VendorEntity {
    @Id
    @Column(name="VENDOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name="CONTACT")
    private String contact;

    @Column(name="PHONE")
    private String phoneNumber;

    @Column(name="EMAIL")
    private String emailAddress;

    @Column(name="ADDRESS")
    private String address;
}
