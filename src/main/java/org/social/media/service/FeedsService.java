package org.social.media.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.social.media.dto.NewsFeed;
import org.social.media.dto.User;
import org.social.media.exception.EmptyPostException;
import org.social.media.exception.UserNotFoundException;
import org.social.media.repository.FeedsRepository;
import org.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedsService {

	@Autowired
	private FeedsRepository feedsRepository;

	@Autowired
	private UserRepository userRepository;

	public String createPost(String userId, String postId, String content) throws Exception {
		userRepository.findById(userId).
		orElseThrow(() -> new UserNotFoundException("User id with "+userId+" not found."));
		if(StringUtils.isEmpty(content) ||  content==null) {
			throw new EmptyPostException("Empty value is passed.");
		} 
		NewsFeed newsFeed = new NewsFeed(userId, postId, content);
		return feedsRepository.createPost(newsFeed);
	}

	public List<NewsFeed> getNewsFeeds(String userId) throws Exception {
		User user = userRepository.findById(userId).
				orElseThrow(() -> new UserNotFoundException("User id with "+userId+" not found."));

		Set<String> followIds= user.getFolloweeIds();
		followIds.add(userId);
		List<NewsFeed> list = feedsRepository.getNewsFeeds(followIds);
		if(list.size()>20) {
			List<NewsFeed> sortedList = list.stream()
					.sorted(Comparator.comparing(NewsFeed :: getPostDateTime).reversed())
					.collect(Collectors.toList());
			list = sortedList.stream().limit(20).collect(Collectors.toList());
		}
		return list;
	}

}
