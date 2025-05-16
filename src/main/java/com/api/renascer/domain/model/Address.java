package com.api.renascer.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country")
    private String country;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "public_place")
    private String publicPlace;
    @Column(name = "number")
    private String number;
    @Column(name = "complement")
    private String complement;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    public Address(String country) {
        this.country = country;
    }
}
