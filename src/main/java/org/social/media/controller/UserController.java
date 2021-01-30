package org.social.media.controller;

import org.social.media.dto.User;
import org.social.media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/{followerId}/follow/{followeeId}")
	public User follow(@PathVariable("followerId") String followerId, @PathVariable("followeeId") String followeeId) throws Exception {
		return userService.follow(followerId, followeeId);
	}
	
	@PostMapping("/{followerId}/unfollow/{followeeId}")
	public User unfollow(@PathVariable("followerId") String followerId, @PathVariable("followeeId") String followeeId) throws Exception {
		return userService.unfollow(followerId, followeeId);
	}
}
