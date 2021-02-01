package org.social.media.service;

import org.social.media.dto.User;
import org.social.media.exception.FollowerNotFoundException;
import org.social.media.exception.SelfFollowException;
import org.social.media.exception.UserNotFoundException;
import org.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	@Autowired
	UserRepository userRepository; 

	public User follow(String followerId, String followeeId) throws Exception {
		User user = userRepository.findById(followerId).
				orElseThrow(() -> new UserNotFoundException("User id with "+followerId+" not found."));
		userRepository.findById(followeeId).
				orElseThrow(() -> new FollowerNotFoundException("User id with "+followeeId+" not found."));
		if(followerId.equals(followeeId)) {
			throw new SelfFollowException("User can not follow to him/her self.");
		}
		user.setUserId(followerId);
		user.getFolloweeIds().add(followeeId);
		return userRepository.save(user);
	}
	public User unfollow(String followerId, String followeeId) throws Exception {
		User user = userRepository.findById(followerId).
				orElseThrow(() -> new UserNotFoundException("User id with "+followerId+" not found."));
		userRepository.findById(followeeId).
				orElseThrow(() -> new FollowerNotFoundException("Follower id with "+followeeId+" not found."));
		user.getFolloweeIds().remove(followeeId);
		return userRepository.save(user);
	}
}
