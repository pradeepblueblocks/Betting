package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.SlotDetails;
import com.bet.BettingGame.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotDetailsRepository extends JpaRepository<SlotDetails,Long> {
    // 15/5/2024 below code is needed
//    @Query("SELECT s FROM SlotDetails s WHERE s.selectedDate = :currentDate AND CAST(:currentTime AS time) BETWEEN s.startTime AND s.endTime")
//    List<SlotDetails> findSlotsForCurrentDate(@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);

//    @Query(value = "EXEC FindSlotsForCurrentDate :currentDate, :currentTime", nativeQuery = true)
//    List<SlotDetails> findSlotsForCurrentDate(@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);

    @Query(value = "EXEC FindSlotsForCurrentDateAndGameId :currentDate, :currentTime, :gameId", nativeQuery = true)
    List<SlotDetails> findSlotsForCurrentDateAndGameId(@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime,@Param("gameId") Long gameId);

    SlotDetails findByStartTime(LocalTime startTime);
    void deleteBySlotAdminIdAndSelectedDate(Long slotAdminId, LocalDate selectedDate);

    List<SlotDetails> findBySlotNumber(int slotNumber);
//void deleteByAdminAndSelectedDate(Long admin, LocalDate selectedDate);

    @Query("SELECT MAX(s.slotNumber) FROM SlotDetails s WHERE s.admin = :admin")
    Integer findMaxSlotNumberByAdmin(@Param("admin") int admin);



//    @Query(value = "EXEC FindTop1StartTimeByCurrentDate :currentDate", nativeQuery = true)
//    LocalTime findTop1StartTimeByCurrentDate(@Param("currentDate") LocalDate currentDate);




    }


