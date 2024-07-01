package com.bet.BettingGame.service;

import com.bet.BettingGame.model.BettingTransaction;
import com.bet.BettingGame.model.UserDetails;

import java.util.List;
import java.util.Optional;

public interface BettingTransactionService {
    void saveTransaction(BettingTransaction transaction);

    Optional<BettingTransaction> findByBetId(Long betId);

    List<BettingTransaction> findTransactionsByuserid(int userid);

    Optional<UserDetails> findUserDetailsByuserid(int userid);
}
