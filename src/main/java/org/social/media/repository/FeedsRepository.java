package org.social.media.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.social.media.dto.NewsFeed;

public interface FeedsRepository {
	public String createPost(NewsFeed newsFeed);
	public List<NewsFeed> getNewsFeeds(Set<String> newsFeed);
	public Optional<NewsFeed> findByPostId(String postId);
}
