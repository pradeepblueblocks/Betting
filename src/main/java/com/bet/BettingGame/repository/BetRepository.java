package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.Bet;

import com.bet.BettingGame.model.BetResultDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long>{


//
//    //Below query is needed  15/5/2024
//@Query("SELECT MAX(b.login_time) FROM Bet b WHERE b.userid = :userid")
//    LocalDateTime findLoginTimeByUserId(@Param("userid")Long userid);
//
//    //Below code is needed 15/5/2024
//    @Query("SELECT b.horseId FROM Bet b " +
//            "WHERE b.slotNumber = :slotNumber " +
//            "AND b.login_time BETWEEN :startTime AND :endTime " +
//            "AND :currentTime > :endTime " + // Ensuring currentTime is after endTime
//            "AND CONVERT(DATE, b.login_time) = :currentDate " + // Truncate time part
//            "GROUP BY b.horseId " +
//            "HAVING COUNT(b.horseId) > 1 " +
//            "ORDER BY COUNT(b.horseId) DESC")
//    List<Long> findHorseIdWithMaxBets(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime, @Param("slotNumber") int slotNumber, @Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalDateTime currentTime);
//
//
//    ///Below query is needed..........15/5/2024
//    @Query("SELECT r.horseId, r.login_time,r.slotNumber,r.userid " +
//            "FROM Bet r " +
//            "WHERE r.login_time <= CURRENT_TIMESTAMP " +
//            "ORDER BY r.login_time DESC")
//    List<Object[]> findLast5BetResults(Pageable pageable);



    @Query(value = "EXEC FindLoginTimeByUserId :userid", nativeQuery = true)
    LocalDateTime findLoginTimeByUserId(@Param("userid") Long userid);

//    @Procedure(name = "FindHorseIdWithMaxBets")
//    List<Long> findHorseIdWithMaxBets(
//            @Param("startTime") LocalDateTime startTime,
//            @Param("endTime") LocalDateTime endTime,
//            @Param("slotNumber") int slotNumber,
//            @Param("currentDate") LocalDate currentDate,
//            @Param("currentTime") LocalDateTime currentTime
//    );

    @Query(value = "EXEC FindHorseIdWithMaxBets :startTime, :endTime, :slotNumber, :currentDate, :currentTime", nativeQuery = true)
    List<Long> findHorseIdWithMaxBets(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("slotNumber") int slotNumber,
            @Param("currentDate") LocalDate currentDate,
            @Param("currentTime") LocalDateTime currentTime
    );

//    @Procedure(name = "FindLast5BetResults")
//    List<Object[]> findLast5BetResults(
//            @Param("pageSize") int pageSize,
//            @Param("pageNumber") int pageNumber
//    );
@Query(value = "EXEC FindLast5BetResults :pageNumber, :pageSize", nativeQuery = true)
//List<Bet> findLast5BetResults(
//        @Param("pageNumber") int pageNumber,
//        @Param("pageSize") int pageSize);

List<Object[]> findLast5BetResults(
        @Param("pageNumber") int pageNumber,
        @Param("pageSize") int pageSize);

//
//    @Query("SELECT b FROM Bet b WHERE b.horseId = :horseId AND b.login_time BETWEEN :startDateTime AND :endDateTime")
//    List<Bet> findBetsByHorseIdAndSlotTime(@Param("horseId") Long horseId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
@Query(value = "EXEC GetBetsByHorseIdAndSlotTime :betItemId, :startDateTime, :endDateTime", nativeQuery = true)
List<Bet> findBetsByHorseIdAndSlotTime(@Param("betItemId") Long horseId,
                                       @Param("startDateTime") LocalDateTime startDateTime,
                                       @Param("endDateTime") LocalDateTime endDateTime);


//    void deleteByGameId(Long gameId);
}






