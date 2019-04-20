package com.java.dto;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class CommentLike {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Timestamp timestamp;


    // Constructors
    public CommentLike() {
    }

    public CommentLike(Comment comment, Timestamp timestamp) {
        this.comment = comment;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    // Utility

    @Override
    public String toString() {
        return "CommentLike{" +
                "id=" + id +
                ", comment=" + comment +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
