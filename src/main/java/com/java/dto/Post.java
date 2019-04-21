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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Friend> getTaggedFriends() {
		return taggedFriends;
	}

	public void setTaggedFriends(List<Friend> taggedFriends) {
		this.taggedFriends = taggedFriends;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<PostLike> getPostLikes() {
		return postLikes;
	}

	public void setPostLikes(List<PostLike> postLikes) {
		this.postLikes = postLikes;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
   
}
