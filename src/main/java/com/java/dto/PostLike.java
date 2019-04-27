package com.java.dto;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class PostLike {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Post post;

    @CreationTimestamp
    @Column(name = "timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Friend author;
    //add author
    // Constructor

    public PostLike() {
    }

    public Friend getAuthor() {
		return author;
	}

	public void setAuthor(Friend author) {
		this.author = author;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public PostLike(Post post, Timestamp timestamp) {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String Timestamp) {
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
