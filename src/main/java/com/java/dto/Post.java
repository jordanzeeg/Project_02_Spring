package com.java.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    int id;

    // FK for Freind
    @Column(name = "owner_id")
    Friend ownerId;

    @Column(name = "description")
    String Description;

    @Column(name = "title")
    String Title;

    // M:N relationship with Freind
    List<Friend> taggedFriends;

    // M:N relationship with Comment
    List<Comment> comments;

    // M:N relationship with Freind? Two list of freinds?
    List<Friend> tagged;

    Date date;
}
