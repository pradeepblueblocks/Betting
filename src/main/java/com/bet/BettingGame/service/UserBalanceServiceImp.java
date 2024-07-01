package com.bet.BettingGame.service;

import com.bet.BettingGame.model.UserBalance;
import com.bet.BettingGame.repository.UserBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBalanceServiceImp implements UserBalanceService{
    @Autowired
    private UserBalanceRepository userBalanceRepository;

    public UserBalance findByUserId(int userId) {
        return userBalanceRepository.findByUserId(userId);
    }

    public void saveUserBalance(UserBalance userBalance) {
        userBalanceRepository.save(userBalance);
    }
}
