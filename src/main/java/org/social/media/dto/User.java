package org.social.media.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "User")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@MongoId
	String userId;
	String name;
	public User() {
	}
	public User(String userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private Set<String> followeeIds = new HashSet<>();
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Set<String> getFolloweeIds() {
		return followeeIds;
	}
	public void setFolloweeIds(Set<String> followeeIds) {
		this.followeeIds = followeeIds;
	}
	
}
