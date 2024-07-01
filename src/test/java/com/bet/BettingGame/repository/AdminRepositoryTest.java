//package com.bet.BettingGame.repository;
//
//
//import com.bet.BettingGame.model.Admin;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.jdbc.Sql;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//class AdminRepositoryTest {
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Test
//    @Sql("/test-data.sql") // Assuming test-data.sql contains test data setup
//    void findByUsername_ShouldReturnAdmin_WhenUsernameExists() {
//        // Act
//        Admin admin = adminRepository.findByUsername("existing_username");
//
//        // Assert
//        assertThat(admin).isNotNull();
//        assertThat(admin.getUsername()).isEqualTo("existing_username");
//    }
//
//    @Test
//    void findByUsername_ShouldReturnNull_WhenUsernameDoesNotExist() {
//        // Act
//        Admin admin = adminRepository.findByUsername("non_existing_username");
//
//        // Assert
//        assertThat(admin).isNull();
//    }
//}
