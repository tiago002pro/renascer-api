package com.api.renascer.video.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "video")
@Entity
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "video_id")
    private String videoId;
    @Column(name = "youtube_frame")
    private String youtubeFrame;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "description")
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
    @Column(name = "date")
    private Date date;
    @Column(name = "cover_image")
    private String coverImage;
    @Column(name = "category")
    private String category;
    @Column(name = "notified")
    private Boolean notified;
}
