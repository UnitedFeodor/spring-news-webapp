package com.htp.springnewswebapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;
    @Column(name = "brief")
    private String brief;
    @Column(name = "content")
    private String content;
    @Column(name = "date_added")
    private LocalDate dateAdded;


    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "authors_user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "news_status_id")
    private NewsStatus status;

}
