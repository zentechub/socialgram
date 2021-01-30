package org.social.media.controller;

import java.net.URI;
import java.util.List;

import org.social.media.dto.NewsFeed;
import org.social.media.service.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class FeedsController {
	
	@Value("${server.domain.name}") 
	private StringBuilder domainName;
	@Autowired
	private FeedsService feedsService;
	
	@PostMapping("/{userId}/post/{postId}")
	public ResponseEntity<String> createPost(@PathVariable("userId") String userId, @PathVariable("postId") String postId, @RequestBody(required = false) String content) throws Exception
	{
		String pstId = feedsService.createPost(userId, postId, content);
		return ResponseEntity.created(URI.create(domainName.append("/feed/post/").append(pstId).toString())).build();
	}
	
	@PostMapping("/{userId}")
	public List<NewsFeed> getNewsFeed(@PathVariable("userId") String userId) throws Exception
	{
		return feedsService.getNewsFeeds(userId);
	}
		
}
