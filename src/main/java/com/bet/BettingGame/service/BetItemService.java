package com.bet.BettingGame.service;

import com.bet.BettingGame.model.BetItem;
import com.bet.BettingGame.model.BetItemDTO;

import java.util.List;
import java.util.Optional;

public interface BetItemService {
    BetItem saveBetItem(BetItem betItem);

//    List<BetItemDTO> getAllBetItems();
List<BetItemDTO> getBetItemsByGameId(Long gameId);

    Optional<BetItemDTO> getBetItemById(Long id);
}
