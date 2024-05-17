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
    @Column(name = "start_date")
    private Date startDate;
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
    @Column(name = "deadline")
    private Date deadline;

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
