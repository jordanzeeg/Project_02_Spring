package com.java.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FRIEND")
public class Friend {
    @GeneratedValue
    @Id
    @Column(name = "id")
    int id;

    int salt;

    @Column(name = "username", unique = true, nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", unique = true, nullable = false)
    String email;

    //TODO: - fix blob
    //Blob profilePic;

    //TODO: - Map M:N rltshp to Post
    List<Post> posts;
}
