package com.api.renascer.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "person")
@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_birth")
    private Date dateBirth;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}
