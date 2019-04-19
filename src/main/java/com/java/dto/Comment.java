package com.java.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
     private String description;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Friend author;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "timestamp")
    @CreationTimestamp
    private Date date;

    @OneToMany(mappedBy = "comment")
    private List<CommentLike> likes;

    // Constructors
    public Comment() {
    }

    public Comment(String description, Friend author, Post post, Date date, List<CommentLike> likes) {
        this.description = description;
        this.author = author;
        this.post = post;
        this.date = date;
        this.likes = likes;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CommentLike> getLikes() {
        return likes;
    }

    public void setLikes(List<CommentLike> likes) {
        this.likes = likes;
    }

    // Utility
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", post=" + post +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }
}
