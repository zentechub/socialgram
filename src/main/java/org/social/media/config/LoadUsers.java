package org.social.media.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.social.media.dto.NewsFeed;
import org.social.media.dto.User;
import org.social.media.repository.FeedsRepository;
import org.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class LoadUsers {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FeedsRepository feedsRepository;
	
	@Bean
	public void importDocumentsFromJsonFile() throws IOException, ParseException {		
		JSONParser parser = new JSONParser();
		JSONArray userData = (JSONArray) parser.parse(new FileReader(ResourceUtils.getFile("classpath:user.json")));
		for (Object userObject : userData)
		  { 
		    JSONObject user = (JSONObject)userObject;
		    String userId = (String) user.get("userId");
		    String name = (String) user.get("name");
		    userRepository.save(new User(userId,name));
		  }
		try {
			Thread.sleep(2000);
			createpostgeneration(parser,userData);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
    }
	
	public void createpostgeneration(JSONParser parser, JSONArray postData) throws IOException, ParseException {
		postData = (JSONArray) parser.parse(new FileReader(ResourceUtils.getFile("classpath:createpost.json")));
		for (Object userObject : postData)
		  { 
		    JSONObject user = (JSONObject)userObject;
		    String userId = (String) user.get("userId");
		    String postId = (String) user.get("postId");
		    String content = (String) user.get("content");
		    feedsRepository.createPost(new NewsFeed(userId,postId,content));
		  }
	}
	
}
