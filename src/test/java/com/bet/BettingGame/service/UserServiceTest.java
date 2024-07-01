//package com.bet.BettingGame.service;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import com.bet.BettingGame.model.UserDetails;
//import com.bet.BettingGame.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImp userService;
//
//    @Test
//    public void testSaveUser() {
//        // Sample user
//        UserDetails user = new UserDetails();
//
//        // Mocking repository method
//        when(userRepository.save(user)).thenReturn(user);
//
//        // Call the method under test
//        UserDetails savedUser = userService.saveUser(user);
//
//        // Verify that the repository's save method was called once with the correct parameter
//        verify(userRepository, times(1)).save(user);
//        assertEquals(user, savedUser);
//    }
//
//    @Test
//    public void testFindById() {
//        // Sample user ID
//        Long userId = 1L;
//        // Sample user
//        UserDetails user = new UserDetails();
//
//        // Mocking repository method
//        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
//
//        // Call the method under test
//        UserDetails foundUser = userService.findById(userId);
//
//        // Verify that the repository's findById method was called once with the correct parameter
//        verify(userRepository, times(1)).findById(userId);
//        assertEquals(user, foundUser);
//    }
//    @Test
//    public void testFindByUsername() {
//        // Sample username
//        String username = "john_doe";
//
//        // Call the method under test
//        String foundUsername = userService.findByUsername(username);
//
//        // Verify that the returned username matches the input username
//        assertEquals(username, foundUsername);
//    }
//
//    @Test
//    public void testAuthenticate_ValidCredentials() {
//        // Sample username and password
//        String username = "john_doe";
//        String password = "password";
//        // Sample user
//        UserDetails user = new UserDetails();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        // Mocking repository method
//        when(userRepository.findByUsername(username)).thenReturn(user);
//
//        // Call the method under test
//        UserDetails authenticatedUser = userService.authenticate(username, password);
//
//        // Verify that the repository's findByUsername method was called once with the correct parameter
//        verify(userRepository, times(1)).findByUsername(username);
//        assertEquals(user, authenticatedUser);
//    }
//
//    @Test
//    public void testAuthenticate_InvalidCredentials() {
//        // Sample username and password
//        String username = "john_doe";
//        String password = "password";
//        // Mocking repository method
//        when(userRepository.findByUsername(username)).thenReturn(null);
//
//        // Call the method under test
//        UserDetails authenticatedUser = userService.authenticate(username, password);
//
//        // Verify that the repository's findByUsername method was called once with the correct parameter
//        verify(userRepository, times(1)).findByUsername(username);
//        assertEquals(null, authenticatedUser);
//    }
//}
//
