package com.jason.service.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import com.jason.domain.Document;
import com.jason.domain.User;

public class File implements Serializable{
	private Document doc;
	private User user;
	
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "File [doc=" + doc + ", user=" + user + "]";
	}
	
	

}
