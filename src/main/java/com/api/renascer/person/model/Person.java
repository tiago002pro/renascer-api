package com.api.renascer.person.model;

import com.api.renascer.person.enums.GenderType;
import com.api.renascer.person.enums.MaritalStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "person")
@Entity(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderType gender;
    @Column(name = "date_birth")
    private String dateBirth;
    @Column(name = "marital_status")
    @Enumerated(EnumType.STRING)
    private MaritalStatusType maritalStatus;
    @Column(name = "cell_phone")
    private String cellPhone;
    @Column(name = "phone")
    private String phone;
    @Column(name = "profile_image")
    private String profileImage;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.address = new Address("Brasil");
    }
}
