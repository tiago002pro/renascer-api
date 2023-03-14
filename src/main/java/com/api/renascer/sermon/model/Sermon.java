package com.api.renascer.sermon.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "sermon")
@Entity
@Data
public class Sermon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "img")
    private String img;
    @Column(name = "title")
    private String title;
    @Column(name = "speaker")
    private String speaker;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    private Date date;
}
