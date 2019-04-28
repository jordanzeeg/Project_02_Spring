package com.java.dto;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.mapping.Join;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "FRIEND")
public class Friend {
    @GeneratedValue
    @Id
    @Column(name = "id")
    private int id;

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


    @ManyToMany( mappedBy = "friends",fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties("friends")
    private List<Post> posts;

    //TODO heres a marker if this fails
    @JsonIgnore
	 @OneToOne(mappedBy = "friend", cascade = { CascadeType.ALL })
    @LazyCollection(LazyCollectionOption.FALSE)
    private Uuidclass uuid;

//	@Id
//  @GeneratedValue(generator = "uuid")
//  @GenericGenerator(name = "uuid", strategy = "uuid2")
//	@Column(name = "resetkey")
//	private UUID resetkey;
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


    @Override public String toString() { return "{\n" + "\"id\":\"" + id + "\",\n" +
            " \"username\":\"" + username + '\"' + ",\n" +
            " \"salt\":\"" + salt + '\"' + ",\n" +
            " \"password\":\"" + password + '\"' + ",\n" +
            " \"firstName\":\"" + firstName + '\"' + ",\n" +
            " \"lastName\":\"" + lastName + '\"' + ",\n" +
            " \"email\":\"" + email + '\"' + "\n" + '}'+ "\n" ; }

	//new thing added by Poho
    public void setSalt(String salt2) {
		this.salt=salt2;
		
	}

    public Friend(String username, String password, String salt, String firstName, String lastName, String email) {
  this.username = username;
  this.password = password;
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.salt = salt;
}

//	public Uuidclass getUuid() {
//		return uuid;
//	}
//
//	public void setUuid(Uuidclass uuid) {
//		this.uuid = uuid;
//	}

}
