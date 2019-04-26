package com.java.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "comment_tbl")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
     private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "friend_id", nullable = false)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Friend author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnoreProperties("comments")
    private Post post;

    @Column(name = "timestamp")
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Timestamp timestamp;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment")
    @JsonIgnoreProperties("comment")
    private List<CommentLike> commentlikes;

    // Constructors
    public Comment() {
    }

    public Comment(String description, Friend author, Post post, Timestamp timestamp, List<CommentLike> likes) {
        this.description = description;
        this.author = author;
        this.post = post;
        this.timestamp = timestamp;
        this.commentlikes = likes;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Friend getAuthor() {
        return author;
    }

    public void setAuthor(Friend author) {
        this.author = author;
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

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<CommentLike> getCommentLikes() {
        return commentlikes;
    }

    public void setCommentLikes(List<CommentLike> likes) {
        this.commentlikes = likes;
    }

    // Utility
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", post=" + post +
                ", timestamp=" + timestamp +
                ", likes=" + commentlikes +
                '}';
    }
}
