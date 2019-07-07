package com.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecommerce.dto.UserDTO;
import com.ecommerce.entity.User;
import com.ecommerce.exception.UserManagementException;
import com.ecommerce.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepo;

	@Mock
	ModelMapper modelMapper;

	UserDTO userDTO;
	User userEntity;

	@Before
	public void setUp() {
		userDTO = new UserDTO(1l, "manish", "me@gmail.com", "manish@123", "seller", "8884959368",
				"#12,Jayanagar,Bengaluru");
		userEntity = new User(1l, "manish", "me@gmail.com", "manish@123", "seller", "8884959368",
				"#12,Jayanagar,Bengaluru");
	}

	@Test
	public void testRegisterUser() throws UserManagementException {

		// positive
		when(modelMapper.map(userDTO, User.class)).thenReturn(userEntity);
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		String actual = userService.registerUser(userDTO);
		assertEquals("User Registration Successful", actual);

		/*
		 * // Negative userDTO = null; userEntity = null; //
		 * when(userRepo.save(userEntity)).thenReturn(null); actual =
		 * userService.registerUser(userDTO);
		 * assertEquals("Something went wrong!! Please contact support team", actual);
		 */

	}
	
	/*
	 * @Test(expected = UserManagementException.class) public void
	 * testRegisterUserNegative() throws UserManagementException {
	 * when(userRepo.save(null)).thenReturn(UserManagementException.class);
	 * userService.registerUser(null); }
	 */
	

	@Test
	public void testUpdateUserProfile() throws UserManagementException {

		// positive
		when(userRepo.findById(userDTO.getUserId())).thenReturn(Optional.of(userEntity));
		when(userRepo.save(userEntity)).thenReturn(userEntity);
		String actual = userService.updateUserProfile(userDTO);
		assertEquals("User Details updated successfully", actual);

		/*
		 * // Negative userDTO.setUserId(2l); userEntity = null; //
		 * when(userRepo.save(userEntity)).thenReturn(null); actual =
		 * userService.updateUserProfile(userDTO);
		 * assertEquals("User with ID "+userDTO.getUserId()+" doesn't exist", actual);
		 */

	}
	
}
