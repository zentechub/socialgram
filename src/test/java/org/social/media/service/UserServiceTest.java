package org.social.media.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.social.media.dto.User;
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
public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@InjectMocks
	private FeedsService feedsService;
	
	@Mock
	public UserRepository userRepository;
	
	@Mock
	public FeedsRepository feedsRepository;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void follow_for_userId_notPresentInDb() throws Exception {
		exception.expect(UserNotFoundException.class);
		exception.expectMessage("User id with 1 not found.");
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		userService.follow("1", "2");
	}
	
	@Test
	public void follow_for_userId_withZeroFolloweeIds() throws Exception {
		exception.expect(FollowerNotFoundException.class);
		exception.expectMessage("User id with 3 not found.");
		when(userRepository.findById(eq("2"))).thenReturn(Optional.of(new User()));
		when(userRepository.findById(eq("3"))).thenReturn(Optional.empty());
		userService.follow("2", "3");
	}
	
	@Test
	public void unfollow_for_userId_notPresentInDb() throws Exception {
		exception.expect(UserNotFoundException.class);
		exception.expectMessage("User id with 1 not found.");
		when(userRepository.findById(any())).thenReturn(Optional.empty());
		userService.unfollow("1", "2");
	}
	
	@Test
	public void unfollow_for_userId_whenFollowerIds_doesnot_exist() throws Exception {
		exception.expect(FollowerNotFoundException.class);
		exception.expectMessage("Follower id with 3 not found.");
		when(userRepository.findById(eq("2"))).thenReturn(Optional.of(new User()));
		when(userRepository.findById(eq("3"))).thenReturn(Optional.empty());
		userService.unfollow("2", "3");
	}
	
	@Test
	public void unfollow_for_userId_withsave() throws Exception {
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		
		User user=new User();
		Set<String> data=new HashSet<>();
		data.add("1");
		user.setFolloweeIds(data);
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		when(userRepository.save(any())).thenReturn(user);
		User actualUser = userService.unfollow("2", "3");
		assertEquals(user, actualUser);
	}
		
}
