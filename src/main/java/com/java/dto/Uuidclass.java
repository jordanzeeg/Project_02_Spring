package com.java.dto;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "Uuidclass")
public class Uuidclass {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "resetkey")
	private UUID resetkey;
	
	 @OneToOne( cascade = { CascadeType.ALL })
	    @JoinColumn(name = "friend_id", nullable = false)
	 @JsonIgnoreProperties("uuid")
	 @LazyCollection(LazyCollectionOption.FALSE)
	 private Friend friend;

	public UUID getResetkey() {
		return resetkey;
	}

	public void setResetkey(UUID resetkey) {
		this.resetkey = resetkey;
	}

	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	public Uuidclass(UUID resetkey, Friend friend) {
		super();
		this.resetkey = resetkey;
		this.friend = friend;
	}

	public Uuidclass() {
		super();
	}
	
}
