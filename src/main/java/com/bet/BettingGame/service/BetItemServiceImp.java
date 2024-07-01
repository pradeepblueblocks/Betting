package com.bet.BettingGame.service;

import com.bet.BettingGame.model.BetItem;
import com.bet.BettingGame.model.BetItemDTO;
import com.bet.BettingGame.repository.BetItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BetItemServiceImp implements BetItemService {

    @Autowired
    private BetItemRepository betItemRepository;

    public BetItem saveBetItem(BetItem betItem) {
        return betItemRepository.save(betItem);
    }

//    public List<BetItemDTO> getAllBetItems() {
//        return betItemRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
//    }

    @Override
    public List<BetItemDTO> getBetItemsByGameId(Long gameId) {
        List<BetItem> betItems = betItemRepository.findByGameId(gameId);
        return betItems.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<BetItemDTO> getBetItemById(Long betItemId) {
        return betItemRepository.findById(betItemId).map(this::convertToDto);
    }

    private BetItemDTO convertToDto(BetItem betItem) {
        BetItemDTO betItemDTO = new BetItemDTO();
        betItemDTO.setBetItemId(betItem.getBetItemId());
        betItemDTO.setGameId(betItem.getGameId());
        betItemDTO.setBetItemName(betItem.getBetItemName());
        return betItemDTO;
    }
}
