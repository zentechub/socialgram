package org.social.media.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "NewsFeed")
public class NewsFeed implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	String userId; 
	@Id
	String postId;
	String content;
	LocalDateTime postDateTime;
		
	public NewsFeed() {
		super();
		postDateTime=LocalDateTime.now();
	}
	
	public LocalDateTime getPostDateTime() {
		return postDateTime;
	}

	public void setPostDateTime(LocalDateTime postDateTime) {
		this.postDateTime = postDateTime;
	}
	
	public NewsFeed(String userId, String postId, String content) {
		this.userId = userId;
		this.postId = postId;
		this.content = content;
		this.postDateTime = LocalDateTime.now();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
	@Override
	public String toString() {
		return "NewsFeed [userId=" + userId + ", postId=" + postId + ", content=" + content + ", postDateTime="
				+ postDateTime + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postDateTime == null) ? 0 : postDateTime.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsFeed other = (NewsFeed) obj;
		if (postDateTime == null) {
			if (other.postDateTime != null)
				return false;
		} else if (!postDateTime.equals(other.postDateTime))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
}
