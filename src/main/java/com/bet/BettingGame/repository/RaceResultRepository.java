package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.RaceResult;
import com.bet.BettingGame.model.RaceResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {

//    15/5/2024 below code is needed
//    @Query("SELECT r.winningHorseIndex, r.betTime,r.slotNumber,r.userid " +
//            "FROM RaceResult r " +
//            "WHERE r.betTime <= CURRENT_TIMESTAMP " +
//            "ORDER BY r.betTime DESC")
//    List<Object[]> findLast5Results(Pageable pageable);


    @Query(value = "EXEC FindLast5RaceResults :pageNumber, :pageSize", nativeQuery = true)
//    List<RaceResultDTO> findLast5Results(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);
    List<Object[]> findLast5Results(@Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize);

    Optional<RaceResult> findBySlotIdAndSlotNumberAndUserid(Long slotId, int slotNumber,int userid);

    @Query("SELECT rr.winningHorseIndex FROM RaceResult rr WHERE rr.slotNumber = :slotNumber")
    Long findWinningHorseIdBySlotNumber(@Param("slotNumber") int slotNumber);

//    void deleteByGameId(Long gameId);

//    void deleteByGameId(Long gameId);



}
