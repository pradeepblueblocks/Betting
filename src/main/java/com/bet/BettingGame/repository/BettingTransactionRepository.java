package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.BettingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BettingTransactionRepository extends JpaRepository<BettingTransaction,Long> {

    Optional<BettingTransaction> findByBetId(Long betId);
    List<BettingTransaction> findByuserid(int userid);
}
