package com.api.renascer.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "start_date")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "address")
    private String address;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "registration")
    private Boolean registration;
    @Column(name = "link")
    private String link;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "deadline")
    private Date deadline;
    @Column(name = "notified")
    private Boolean notified;

    public Schedule() {

    }

    public Schedule(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadline = endDate;
        this.registration = Boolean.FALSE;
    }
}
