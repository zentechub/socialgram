package org.social.media.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.social.media.repository.impl.FeedsRepositoryImpl;
import org.social.media.service.FeedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FeedsControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@InjectMocks
	private FeedsService feedsService;
	
	@InjectMocks
	FeedsController feedsController;
	
	@Mock
	FeedsRepositoryImpl feedsRepositoryImpl;
	
	@Before
	public void setup() throws Exception {
		this.mvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void createPost() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/user/1/post/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content("This is first post of first user"))
				.andExpect(status().isCreated())
				.andReturn();
	}

	@Test
	public void getNewsFeeds_ok() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/post/1"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void follow_user() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/1/follow/2"))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void unfollow_user() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/1/follow/2"))
				.andExpect(status().isOk())
				.andReturn();
	}

}
