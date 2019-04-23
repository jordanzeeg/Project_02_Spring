package com.java.dto;

import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FRIEND")
public class Friend {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

    //private int salt;
    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @ManyToMany
    @JoinTable(
            name = "FRIEND_POST",
            joinColumns = {@JoinColumn(name = "friend_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private List<Post> posts;

    //TODO: - add property for profile pic (S3)

    // Constructors

    public Friend() {
    }

    public Friend(String username, String password, String salt, String firstName, String lastName, String email,
                  List<Post> posts) {
        this.salt = salt;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.posts = posts;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() { return salt; }

    public void setSalt() { this.salt = salt; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


    // Utility

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", posts=" + posts +
                '}';
    }
}
