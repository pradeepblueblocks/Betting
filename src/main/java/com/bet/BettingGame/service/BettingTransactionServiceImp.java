package com.bet.BettingGame.service;

import com.bet.BettingGame.model.BettingTransaction;
import com.bet.BettingGame.model.UserDetails;
import com.bet.BettingGame.repository.BettingTransactionRepository;
import com.bet.BettingGame.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BettingTransactionServiceImp implements BettingTransactionService {

    @Autowired
    private BettingTransactionRepository bettingTransactionRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;


    public void saveTransaction(BettingTransaction transaction) {
        bettingTransactionRepository.save(transaction);
    }
    @Override
    public Optional<BettingTransaction> findByBetId(Long betId) {
        return bettingTransactionRepository.findByBetId(betId);
    }

    public List<BettingTransaction> findTransactionsByuserid(int userid) {
        return bettingTransactionRepository.findByuserid(userid);
    }

    public Optional<UserDetails> findUserDetailsByuserid(int userid) {
        return userDetailsRepository.findById(userid);
    }

}
