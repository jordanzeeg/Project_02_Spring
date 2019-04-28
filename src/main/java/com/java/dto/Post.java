package com.java.dto;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
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

    @ManyToMany( cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @JoinTable(
            name = "FRIEND_POST",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Friend> friends;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", cascade = { CascadeType.ALL })
    private List<Comment> comments;

    @OneToMany( mappedBy = "post", cascade = { CascadeType.ALL })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnoreProperties("post")
    private List<PostLike> postLikes;

    @Column(name = "timestamp")
    @CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Timestamp timestamp;

    // Constructors
	public Post() {
    }

    public Post(String description, String title, List<Friend> friends, List<Comment> comments,
                List<PostLike> postLikes, Timestamp timestamp) {
        this.description = description;
        this.title = title;
        this.friends = friends;
        this.comments = comments;
        this.postLikes = postLikes;
        this.timestamp = timestamp;
    }

    // Utility

    @Override
    public String toString() {
        return "{\n" +
                "\"id:\"\"" + id + '\"' + ",\n" +
                "\"description='" + description + '\"' + ",\n" +
                "\"title='" + title + '\"' + ",\n" +
                "\"timestamp='" + timestamp +'\"' + "\n" + '}'+ "\n" ; 
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
	
	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
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
