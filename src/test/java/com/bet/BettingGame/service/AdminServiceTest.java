//package com.bet.BettingGame.service;
//import com.bet.BettingGame.model.Admin;
//import com.bet.BettingGame.repository.AdminRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class AdminServiceTest {
//
//    @Mock
//    private AdminRepository adminRepository;
//
//    @InjectMocks
//    private AdminService adminService = new AdminServiceImp(adminRepository);
////
////    @Test
////    void testGetAllDetails() {
////        // Mock the behavior of the repository
////        List<Admin> admins = List.of(new Admin(), new Admin());
////        when(adminRepository.findAll()).thenReturn(admins);
////
////        // Call the service method
////        List<Admin> result = adminService.getAllDetails();
////
////        // Assert the result
////        assertEquals(admins.size(), result.size());
////    }
//
////    @Test
////    void testAuthenticate() {
////        // Mock the behavior of the repository
////        String username = "test_user";
////        String password = "test_password";
////        Admin admin = new Admin();
////        when(adminRepository.findByUsername(username)).thenReturn(admin);
////
////        // Call the service method
////        Admin result = adminService.authenticate(username, password);
////
////        // Assert the result
////        assertEquals(admin, result);
////    }
////
////    @Test
////    void testAuthenticate_Successful() {
////        // Mock the behavior of the repository
////        String username = "test_user";
////        String password = "test_password";
////        Admin admin = new Admin();
////        admin.setUsername(username);
////        admin.setPassword(password);
////        when(adminRepository.findByUsername(username)).thenReturn(admin);
////
////        // Call the service method
////        Admin result = adminService.authenticate(username, password);
////
////        // Assert the result
////        assertEquals(admin, result);
////    }
//
//
//
//
//
//}
//
//
