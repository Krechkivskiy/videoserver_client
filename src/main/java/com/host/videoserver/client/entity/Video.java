package com.host.videoserver.client.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "videos")
@ToString
public class Video  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "watching_count")
    private long watchingCount;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private User user;

    @Column(name = "proprietary")
    private boolean open;
}
