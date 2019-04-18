package com.java.dto;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Like {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    //TODO: - Map FK to Post
    @Column(name = "post_id")
    int postId;

    //TODO: - Map FK to Comment, 1:N rltshp to Like
    @Column(name = "comment_id")
    int commentId;

    @Column(name = "timestamp")
    @CreationTimestamp
    Date date;
}
