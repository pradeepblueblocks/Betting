package com.bet.BettingGame.service;

import com.bet.BettingGame.model.UserBalance;

public interface UserBalanceService {
    UserBalance findByUserId(int userId);
   void saveUserBalance(UserBalance userBalance);


}
