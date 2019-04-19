package com.java.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

public class PostLike {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @CreationTimestamp
    @Column(name = "timestamp")
    private String timestamp;


    // Constructor

    public PostLike() {
    }

    public PostLike(Post post, String timestamp) {
        this.post = post;
        this.timestamp = timestamp;
    }

    // Getter and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Utility

    @Override
    public String toString() {
        return "PostLike{" +
                "id=" + id +
                ", post=" + post +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
