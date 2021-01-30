package org.social.media.repository;

import java.util.Optional;

import org.social.media.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
	public User save(User user);
	public Optional<User> findById(String userId);
}
