package com.bet.BettingGame.repository;

import com.bet.BettingGame.model.Slot;
import com.bet.BettingGame.model.SlotDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot,Long> {

//    @Query("SELECT slotAdminId FROM Slot WHERE adminStartTime = :adminStartTime AND adminEndTime = :adminEndTime")
//    Long findIdByAdminStartTimeAndAdminEndTime(@Param("adminStartTime") LocalTime adminStartTime, @Param("adminEndTime") LocalTime adminEndTime);


    @Query(value = "EXEC GetSlotAdminIdByTime @adminStartTime = :adminStartTime, @adminEndTime = :adminEndTime, @gameId = :gameId, @selectedDate = :selectedDate", nativeQuery = true)
    Long getSlotAdminIdByTime(@Param("adminStartTime") Time adminStartTime,
                              @Param("adminEndTime") Time adminEndTime,
                              @Param("gameId") Long gameId,
                              @Param("selectedDate") LocalDate selectedDate);



    @Query(value = "EXEC CheckForSlotOverlap :gameId, :selectedDate, :adminStartTime, :adminEndTime", nativeQuery = true)
    List<Slot> findByGameIdAndSelectedDateAndAdminTimeRange(
            @Param("gameId") Long gameId,
            @Param("selectedDate") Date selectedDate,
            @Param("adminStartTime") Time adminStartTime,
            @Param("adminEndTime") Time adminEndTime
    );
//    @Query(value = "EXEC FindAdminSlotsForCurrentDate :currentDate, :currentTime", nativeQuery = true)
//    List<Slot> findAdminSlotsForCurrentDate(@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);

    @Query(value = "EXEC FindAdminSlotsForCurrentDate :currentDate, :currentTime", nativeQuery = true)
    List<Object[]> findAdminSlotsForCurrentDate(@Param("currentDate") LocalDate currentDate, @Param("currentTime") LocalTime currentTime);


    Optional<Slot> findByGameId(Long gameId);
}
