package com.java.dto;

public class Messengering {
	private int status;
	private String menagerie;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMenagerie() {
		return menagerie;
	}

	public void setMenagerie(String menagerie) {
		this.menagerie = menagerie;
	}



	public Messengering(int status, String menagerie) {
		super();
		this.status = status;
		this.menagerie = menagerie;
	}

	public Messengering() {
		super();
	}
}
