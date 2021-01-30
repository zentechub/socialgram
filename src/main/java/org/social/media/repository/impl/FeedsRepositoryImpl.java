package org.social.media.repository.impl;

import java.util.List;
import java.util.Set;


import org.social.media.dto.NewsFeed;
import org.social.media.repository.FeedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FeedsRepositoryImpl implements FeedsRepository{
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String createPost(NewsFeed newsFeed) {
		return mongoTemplate.save(newsFeed).getPostId();
	}

	@Override
	public List<NewsFeed> getNewsFeeds(Set<String> followerIds) {
		Query query=new Query();
		query.addCriteria(Criteria.where("userId").in(followerIds)).with(Sort.by(Direction.DESC, "postDateTime")).limit(20);
		return mongoTemplate.find(query, NewsFeed.class);		
	}	
	
}
