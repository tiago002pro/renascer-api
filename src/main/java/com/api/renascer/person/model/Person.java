package com.api.renascer.person.model;

import com.api.renascer.person.enums.GenderType;
import com.api.renascer.person.enums.MaritalStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Column(name = "local_church")
    private String localChurch;
    @Column(name = "relationship_church")
    private String relationshipChurch;
    @Column(name = "entry_date")
    private String entryDate;
    @Column(name = "entry_by")
    private String entryBy;
    @Column(name = "came_from")
    private String cameFrom;
    @Column(name = "baptized")
    private Boolean baptized;
    @Column(name = "accepted_jesus")
    private Boolean acceptedJesus;
    @Column(name = "leader")
    private Boolean leader;
    @Column(name = "pastor")
    private Boolean pastor;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.address = new Address("Brasil");
    }
}
