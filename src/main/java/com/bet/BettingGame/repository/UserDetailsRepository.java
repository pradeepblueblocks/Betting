package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
}
