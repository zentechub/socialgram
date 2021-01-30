package org.social.media.repository.impl;

import java.util.Optional;

import org.social.media.dto.User;
import org.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public User save(User user) {
		return mongoTemplate.save(user);
	}

	@Override
	public Optional<User> findById(String userId) {
		return Optional.ofNullable(mongoTemplate.findById(userId, User.class));
	}	
}
