package com.java.dto;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Friend owner;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    //TODO: - Add property for post url image(S3)

    @OneToMany(mappedBy = "post")
    private List<Friend> taggedFriends;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    @Column(name = "timestamp")
    private String timestamp;

    // Constructors

    public Post() {
    }

    public Post(Friend owner, String description, String title, List<Friend> taggedFriends, List<Comment> comments,
                List<PostLike> postLikes, String timestamp) {
        this.owner = owner;
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
                ", owner=" + owner +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", taggedFriends=" + taggedFriends +
                ", comments=" + comments +
                ", postLikes=" + postLikes +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
