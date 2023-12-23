package com.api.renascer.schedule.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "schedule")
@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
    @Column(name = "image")
    private String image;
}
