package org.social.media.config;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.social.media.dto.User;
import org.social.media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class LoadUsers {
	
	@Autowired
	UserRepository userRepository;
	
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
		
    }
	
}
