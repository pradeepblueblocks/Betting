package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.BetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetItemRepository extends JpaRepository<BetItem,Long> {

    List<BetItem> findByGameId(Long gameId);
//    void deleteByGameId(Long gameId);
}
