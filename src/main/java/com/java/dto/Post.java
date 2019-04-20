package com.java.dto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    //TODO: - Add property for post url image(S3)

    @ManyToMany(mappedBy = "posts")
    private List<Friend> taggedFriends;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    // Constructors

    public Post() {
    }

    public Post(String description, String title, List<Friend> taggedFriends, List<Comment> comments,
                List<PostLike> postLikes, Timestamp timestamp) {
        this.description = description;
        this.title = title;
        this.taggedFriends = taggedFriends;
        this.comments = comments;
        this.postLikes = postLikes;
        this.timestamp = timestamp;
    }

    // Utility

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", taggedFriends=" + taggedFriends +
                ", comments=" + comments +
                ", postLikes=" + postLikes +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
