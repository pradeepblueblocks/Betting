package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Long> {

    UserBalance findByUserId(int userId);
}
