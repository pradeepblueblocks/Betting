//package com.bet.BettingGame.service;
//
//
//import com.bet.BettingGame.model.Admin;
//import com.bet.BettingGame.repository.AdminRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//class AdminServiceImpTest {
//
//    @Mock
//    private AdminRepository adminRepository;
//
//    @InjectMocks
//    private AdminServiceImp adminService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void findById_ShouldReturnAdmin_WhenAdminExists() {
//        // Arrange
//        Admin admin = new Admin();
//        admin.setId(1L);
//        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
//
//        // Act
//        Admin result = adminService.findById(1L);
//
//        // Assert
//        assertEquals(admin, result);
//    }
//
//    @Test
//    void findById_ShouldReturnNull_WhenAdminDoesNotExist() {
//        // Arrange
//        when(adminRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act
//        Admin result = adminService.findById(1L);
//
//        // Assert
//        assertNull(result);
//    }
//
//    @Test
//    void findById_ShouldReturnNull_WhenExceptionOccurs() {
//        // Arrange
//        when(adminRepository.findById(anyLong())).thenThrow(new RuntimeException("Database error"));
//
//        // Act
//        Admin result = adminService.findById(1L);
//
//        // Assert
//        assertNull(result);
//    }
//}
