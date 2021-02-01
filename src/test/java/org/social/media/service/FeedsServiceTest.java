package org.social.media.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.social.media.dto.NewsFeed;
import org.social.media.dto.User;
import org.social.media.exception.EmptyPostException;
import org.social.media.exception.FollowerNotFoundException;
import org.social.media.exception.UserNotFoundException;
import org.social.media.repository.FeedsRepository;
import org.social.media.repository.UserRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class})
public class FeedsServiceTest {

	@InjectMocks
	private FeedsService feedsService;

	@Mock
	public FeedsRepository feedsRepository;

	@Mock
	public UserRepository userRepository;

	@Mock
	public User user;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);		
	}

	@Test
	public void createPost_withEmptyPostCotent_tryCatchIdiom() throws Exception {
		try {
			when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
			feedsService.createPost("1", "1", "");
			fail("It should throw EmptyPostException");
		} catch (EmptyPostException e) {
			Assertions.assertThat(e)
			.isInstanceOf(EmptyPostException.class)
			.hasMessage("Empty value is passed.");
		}
	}

	@Test
	public void getNewsFeed_for_userId_withValidInput() throws Exception {
		User user=new User();
		Set<String> data=new HashSet<>();
		data.add("1");
		user.setFolloweeIds(data);
		List<NewsFeed> newsFeed= Collections.singletonList(new NewsFeed());
		when(userRepository.findById(any())).thenReturn(Optional.of(user));
		when(feedsRepository.getNewsFeeds(any())).thenReturn(newsFeed);
		List<NewsFeed> actualNewsFeed = feedsService.getNewsFeeds("1");
		assertEquals(newsFeed,actualNewsFeed );
	}

	@Test(expected = EmptyPostException.class)
	public void createPost_withInvalidInput_testExpected() throws Exception {
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		feedsService.createPost("1", "1", "");
	}

	@Test(expected = UserNotFoundException.class)
	public void createPost_withInvalidUser() throws Exception {
		when(userRepository.findById(eq("11"))).thenReturn(Optional.empty());
		feedsService.createPost("11", "11", "This is eleventh post from eleventh user");
	}

	@Test
	public void createPost_withInvalidInput_ExpectedExceptionRule() throws Exception {
		exception.expect(EmptyPostException.class);
		exception.expectMessage("Empty value is passed.");
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		feedsService.createPost("1", "4", "");
	}

	@Test
	public void getNewsFeed_for_userId_notPresentInDb() throws Exception {
		exception.expect(UserNotFoundException.class);
		exception.expectMessage("User id with 1 not found.");
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		feedsService.getNewsFeeds("1");
	}

	@Test
	public void getNewsFeed_for_userId_with20Feeds() throws Exception {
		List<NewsFeed> expectedList = buildNewsFeeds(20);
		when(userRepository.findById(any())).thenReturn(Optional.of(buildUserWithFolloweeIds()));
		when(feedsRepository.getNewsFeeds(any())).thenReturn(expectedList);

		List<NewsFeed> actualList = feedsService.getNewsFeeds("1");
		assertEquals(20, actualList.size());
		assertEquals(expectedList, actualList);
	}

	@Test
	public void getNewsFeed_for_userId_withMoreThan20Feeds() throws Exception {
		List<NewsFeed> expectedList = buildNewsFeeds(30);
		when(userRepository.findById(any())).thenReturn(Optional.of(buildUserWithFolloweeIds()));
		when(feedsRepository.getNewsFeeds(any())).thenReturn(expectedList);

		List<NewsFeed> actualList = feedsService.getNewsFeeds("1");
		assertEquals(20, actualList.size());
	}

	@Test
	public void createPost_withUser() throws Exception {
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		when(feedsRepository.createPost(any())).thenReturn("2");
		String pstId = feedsService.createPost("2", "2", "This is Sixth post from Sixth user");
		assertEquals(2, Integer.parseInt(pstId));
	}

	private List<NewsFeed> buildNewsFeeds(int size) {
		List<NewsFeed> list = new ArrayList<>();

		for(int i=0; i<size; i++) {
			NewsFeed news = new NewsFeed();
			news.setPostDateTime(LocalDateTime.now());
			list.add(news);
		};
		return list;
	}
	private User buildUserWithFolloweeIds() {
		User user=new User();
		Set<String> data=new HashSet<>();
		data.add("1");
		user.setFolloweeIds(data);
		return user;
	}

}
