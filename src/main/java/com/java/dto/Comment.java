package com.java.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    @Column(name = "description")
    String description;

    //TODO: - Map FK to Freind
    @Column(name = "author_id")
    Friend authorId;

    //TODO: - Map FK to Post
    @Column(name = "post_id")
    Post postId;

    @Column(name = "timestamp")
    @CreationTimestamp
    Date date;

    //TODO: - Map M:N rltshp to Like
    List<Like> likes;

}
