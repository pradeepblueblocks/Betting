package com.bet.BettingGame.controller;



import com.bet.BettingGame.model.*;
//import com.bet.BettingGame.model.Slot;
import com.bet.BettingGame.repository.*;
//import com.bet.BettingGame.service.RaceService;
import com.bet.BettingGame.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

     @Autowired
     private SlotDetailsRepository slotDetailsRepository;
     @Autowired
     private BetService betService;
     @Autowired
     private  BetRepository betRepository;
     @Autowired
     private RaceResultRepository raceResultRepository;
     @Autowired
     private RaceResultService raceResultService;
     @Autowired
     private SlotDetailsService slotService;
     @Autowired
     private UserBalanceService userBalanceService;
     @Autowired
     private BetItemRepository betItemRepository;
     @Autowired
     private UserDetailsRepository userDetailsRepository;
     @Autowired
     private GameRepository gameRepository;
     @Autowired
     private BettingTransactionService bettingTransactionService;



    @PostMapping("/upcomingSlots")
    public ResponseEntity<Map<String, Object>> getUserPage(@RequestBody SlotDetailsRequest slotDetailsRequest) {
        log.info("Entered into GetMapping upcomingSlots endpoint...");

        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalTime currentTime = currentDateTime.toLocalTime();
            LocalDate currentDate = LocalDate.now();

            Long gameId = slotDetailsRequest.getGameId();

            // Retrieve upcoming slots using the stored procedure
//            List<SlotDetails> upcomingSlots = slotDetailsRepository.findSlotsForCurrentDate(currentDate, currentTime);

            List<SlotDetails> upcomingSlots = slotDetailsRepository.findSlotsForCurrentDateAndGameId(currentDate, currentTime,gameId);
            log.info("Upcoming Slots: {}", upcomingSlots);

            // Map SlotDetails to SlotDTO
            List<SlotDetailsDTO> slotDTOs = upcomingSlots.stream()
                    .map(slot -> new SlotDetailsDTO(slot.getSlotId(), slot.getStartTime(), slot.getEndTime(), slot.getSlotNumber()))
                    .collect(Collectors.toList());

//            session.setAttribute("loginTime", currentDateTime);

            Map<String, Object> response = new HashMap<>();
//            if (!slotDTOs.isEmpty()) {
//                SlotDetailsDTO slot = slotDTOs.get(0);
//                session.setAttribute("startDateTime", currentDate.atTime(slot.getStartTime()));
//                session.setAttribute("endDateTime", currentDate.atTime(slot.getEndTime()));
//                session.setAttribute("slotId", slot.getSlotId());
//                session.setAttribute("slotNumber", slot.getSlotNumber());
//                session.setAttribute("currentTime", currentTime);
//            }

            response.put("slots", slotDTOs);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An error occurred in GetMapping Bet Method: " + e.getMessage(), e);

            // Return an appropriate error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while fetching slot details.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }




    @PostMapping("/Bet")
    public ResponseEntity<String> saveBet(@RequestBody BetRequest betRequest) {
        log.info("BetAmount: {}, selectedHorse: {}, user: {}, slotNumber:{},gameId:{}",
                betRequest.getBetAmount(), betRequest.getSelectedHorse(),
                betRequest.getUserid(), betRequest.getSlotNumber(), betRequest.getGameId());

        try {
            float betAmount = betRequest.getBetAmount();
            Long selectedHorse = betRequest.getSelectedHorse();
            int user = betRequest.getUserid();
            int slotNumber = betRequest.getSlotNumber();
            Long gameId = betRequest.getGameId();

            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalTime currentActualTime = currentDateTime.toLocalTime();
            log.info("Current time: {}", currentActualTime);

            List<SlotDetails> slotObjects = slotService.findSlotsByNumber(slotNumber);

            if (slotObjects == null || slotObjects.isEmpty()) {
                log.info("Slot object not found for slot number {}", slotNumber);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid slot number");
            }

            SlotDetails validSlot = null;
            for (SlotDetails slotObject : slotObjects) {
                LocalTime startTime = slotObject.getStartTime();
                LocalTime endTime = slotObject.getEndTime();
                if (currentActualTime.isAfter(startTime) && currentActualTime.isBefore(endTime)) {
                    validSlot = slotObject;
                    break;
                }
            }

            if (validSlot == null) {
                log.info("Betting time is over for slot number {}", slotNumber);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Betting time is over for the selected slot number");
            }

            // Check user wallet balance
            UserBalance userBalance = userBalanceService.findByUserId(user);

            if (userBalance.getWalletBalance() < betAmount) {
                log.info("Insufficient balance for user {}", user);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance. Please add funds to your wallet.");
            }

            // Deduct the bet amount from the user's wallet
            userBalance.setWalletBalance(userBalance.getWalletBalance() - betAmount);
            userBalanceService.saveUserBalance(userBalance);

            Long slotId = validSlot.getSlotId();
            log.info("SlotID: {}", slotId);

            Bet bet = new Bet();
            bet.setUserid(user);
            bet.setBetAmount(betAmount);
            bet.setBetItemId(selectedHorse);
            bet.setGameId(gameId);
            bet.setLogin_time(currentDateTime);
            bet.setSlotId(slotId);
            bet.setSlotNumber(slotNumber);

            betService.saveBet(bet);
            Long betId = bet.getBetId(); // Get the betId from the saved Bet object
            log.info("BetID: {}", betId);


            // Save to betting transaction table
            BettingTransaction transaction = new BettingTransaction();
            transaction.setUserid(user);
            transaction.setBetAmount(betAmount);
            transaction.setBetTime(currentDateTime);
            transaction.setSlotNumber(slotNumber);
            transaction.setSlotId(slotId);
            transaction.setGameId(gameId);
            transaction.setBetId(betId);
            transaction.setWinningHorseIndex(selectedHorse);// Set the betId in the BettingTransaction object
           // transaction.setBetItemName("Unknown"); // Placeholder, update as needed
            //transaction.setGameName("Unknown"); // Placeholder, update as needed

             // Placeholder, update as needed
            //transaction.setUsername("Unknown"); // Placeholder, update as needed
            transaction.setWinningPriceAwarded(false);
            transaction.setWalletBalance(userBalance.getWalletBalance()); // Set the wallet balance


            bettingTransactionService.saveTransaction(transaction);

            return ResponseEntity.ok("Bet saved successfully!");
        } catch (Exception e) {
            log.error("An error occurred while saving the bet: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while saving the bet");
        }
    }




    @PostMapping("/slotDetails")
    public ResponseEntity<SlotDetailsDTO> getSlotDetails(@RequestBody SlotRequest slotRequest) {
        try {
            Long slotId = slotRequest.getSlotId();
            Optional<SlotDetails> slotObjects = slotService.findById(slotId);

            if (!slotObjects.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            SlotDetails slotDetails = slotObjects.get();
            SlotDetailsDTO slotDetailsDTO = new SlotDetailsDTO(
                    slotDetails.getSlotId(),
                    slotDetails.getStartTime(),
                    slotDetails.getEndTime(),
                    slotDetails.getSlotNumber()

            );

            return ResponseEntity.ok(slotDetailsDTO);
        } catch (Exception e) {
            log.error("An error occurred while fetching slot details: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping("/winningDetails")
    public ResponseEntity<Object> getWinningDetails(@RequestBody RaceResultRequest raceResultRequest) {
        try {
            int slotNumber = raceResultRequest.getSlotNumber();
            Long user = raceResultRequest.getUserid();
            Long slotId = raceResultRequest.getSlotId();
            LocalTime startTime = raceResultRequest.getStartTime();
            LocalTime endTime = raceResultRequest.getEndTime();
            Long gameId = raceResultRequest.getGameId();

            log.info("Request from front: userid : {} slotId: {} startTime: {} endTime: {} gameId: {}", user, slotId, startTime, endTime, gameId);

            LocalDate currentDate = LocalDate.now();
            log.info("USER: {}", user);

            LocalDateTime loginTime = betService.findLoginTimeByUserId(user);
            log.info("Slot LoginTime: {}", loginTime);

            LocalDateTime startDateTime = currentDate.atTime(startTime);
            LocalDateTime endDateTime = currentDate.atTime(endTime);
            log.info("Slot endDateTime: {}", endDateTime);
            log.info("Slot startDateTime: {}", startDateTime);

            LocalDateTime currentTime = LocalDateTime.now();

            Long horseIdWithMaxBets = betService.findHorseIdWithMaxBets(startDateTime, endDateTime, slotNumber, currentDate, currentTime);
            log.info("Winning HorseId: {}", horseIdWithMaxBets);

            if (horseIdWithMaxBets != null) {
                List<Bet> winningBets = betService.findBetsByHorseIdAndSlotTime(horseIdWithMaxBets, startDateTime, endDateTime);


                // Check if there is more than one bet
                if (winningBets.size() <= 1) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Not enough bets to determine a winner");
                    return ResponseEntity.ok(response);
                }



                Set<Integer> processedUsers = new HashSet<>();

                // Fetch winning price
                Optional<SlotDetails> slotDetailsOptional = slotDetailsRepository.findById(slotId);
                Float winningPrice = 0.0f;
                if (slotDetailsOptional.isPresent()) {
                    SlotDetails slotDetails = slotDetailsOptional.get();
                    winningPrice = slotDetails.getWinningPrice();
                    log.info("Winning Price: {}", winningPrice);
                }

                for (Bet bet : winningBets) {
                    Optional<RaceResult> raceResultOptional = raceResultService.findBySlotIdAndSlotNumberAndUserId(slotId, slotNumber, bet.getUserid());
                    RaceResult raceResult = raceResultOptional.orElseGet(RaceResult::new);

                    raceResult.setSlotNumber(slotNumber);
                    raceResult.setSlotId(slotId);
                    raceResult.setBetTime(bet.getLogin_time());
                    raceResult.setUserid(bet.getUserid());
                    raceResult.setWinningHorseIndex(horseIdWithMaxBets);

                    // Fetch username
                    Optional<UserDetails> userDetailsOptional = userDetailsRepository.findById(bet.getUserid());
                    if (userDetailsOptional.isPresent()) {
                        UserDetails userDetails = userDetailsOptional.get();
                        raceResult.setUsername(userDetails.getUsername());
                        log.info("Username : {}", userDetails.getUsername());
                    } else {
                        raceResult.setUsername("Unknown");
                    }

                    // Fetch betItemName
                    Optional<BetItem> betItemOptional = betItemRepository.findById(horseIdWithMaxBets);
                    if (betItemOptional.isPresent()) {
                        BetItem betItem = betItemOptional.get();
                        raceResult.setBetItemName(betItem.getBetItemName());
                        log.info("Bet Item Name: {}", betItem.getBetItemName());
                    } else {
                        raceResult.setBetItemName("Unknown");
                    }

                    // Fetch game name to be saved in raceresult table
                    Optional<Game> gameItemOptional = gameRepository.findById(gameId);
                    if (gameItemOptional.isPresent()) {
                        Game gameitem = gameItemOptional.get();
                        raceResult.setGameName(gameitem.getGameName());
                        log.info("Game Item Name: {}", gameitem.getGameName());
                    } else {
                        raceResult.setGameName("Unknown");
                    }

                    raceResult.setWinningPrice(winningPrice);

                    // Update or save the race result
                    raceResultService.updateOrSaveRaceResult(raceResult, endDateTime);
                    Long raceResultId = raceResult.getId(); // Get the id from the saved RaceResult object
                    log.info("RaceResultID: {}", raceResultId);

                    // Check if the user has already been awarded the winning price for this slot
                    if (!processedUsers.contains(bet.getUserid()) && !raceResult.isWinningPriceAwarded()) {
                        UserBalance userBalance = userBalanceService.findByUserId(bet.getUserid());
                        if (userBalance != null) {
                            userBalance.setWalletBalance(userBalance.getWalletBalance() + winningPrice);
                            userBalanceService.saveUserBalance(userBalance);
                            raceResult.setWinningPriceAwarded(true);
                            raceResultService.save(raceResult);
                            log.info("Updated balance for user {}: {}", bet.getUserid(), userBalance.getWalletBalance());


                            // Create a new transaction
                            BettingTransaction transaction = new BettingTransaction();
                            transaction.setUserid(bet.getUserid());
                            transaction.setBetAmount(bet.getBetAmount());
                            transaction.setBetTime(bet.getLogin_time());
                            transaction.setSlotNumber(slotNumber);
                            transaction.setSlotId(slotId);
                            transaction.setGameId(gameId);
                            transaction.setBetId(bet.getBetId());
                            transaction.setWinningHorseIndex(horseIdWithMaxBets);
                            transaction.setWinningPrice(winningPrice);
                            transaction.setWinningPriceAwarded(true);
                            transaction.setWalletBalance(userBalance.getWalletBalance());
                            transaction.setId(raceResultId);

                            bettingTransactionService.saveTransaction(transaction);


//                            }
                        }
                        processedUsers.add(bet.getUserid());
                    }
                }
            }

            Long winningHorseId = raceResultService.getWinningHorseIdBySlotNumber(slotNumber);
            if (winningHorseId == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "No Winning Horse Found");
                return ResponseEntity.ok(response);
            } else {
                Optional<BetItem> betItemOptional = betItemRepository.findById(winningHorseId);
                if (betItemOptional.isPresent()) {
                    RaceResultDTO obj = new RaceResultDTO();
                    BetItem betItem = betItemOptional.get();
                    String betItemName = betItem.getBetItemName();
                    obj.setWinningItemName(betItemName);
                    obj.setWinningHorseIndex(winningHorseId);
                    return ResponseEntity.ok(obj);
                } else {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Winning Horse ID not found in BetItem repository");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            }

        } catch (Exception e) {
            log.error("An error occurred while fetching winning details: " + e.getMessage(), e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "An error occurred while fetching winning details. Please try again later.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



    @GetMapping("/winningHorseView")
    public ResponseEntity<Map<String, Object>> getWinningHorse(HttpSession session) {
        try {

            int pageNumber = 0; // Page number
            int pageSize = 5;   // Page size

            List<Object[]> last5Result = raceResultRepository.findLast5Results(pageNumber, pageSize);
            log.info("LAST5RACERESULTS: " + last5Result);



            List<RaceResultDTO> last5Results = last5Result.stream().map(record -> {
                RaceResultDTO dto = new RaceResultDTO();
                dto.setWinningHorseIndex(((BigInteger) record[0]).longValue());
                dto.setBetTime(((Timestamp) record[1]).toLocalDateTime());
                dto.setSlotNumber((Integer) record[2]);
//                dto.setUserid((Integer) record[3]);
                dto.setUsername((String) record[3]);
                dto.setGameName((String) record[4]);
                dto.setWinningItemName((String) record[5]);
//                dto.setUserid(((BigInteger) record[3]).longValue());
                return dto;
            }).collect(Collectors.toList());

            List<Object[]> last5BetResult = betRepository.findLast5BetResults(pageNumber, pageSize);

            List<BetResultDTO> last5BetResults = last5BetResult.stream().map(record -> {
                BetResultDTO dto = new BetResultDTO();
                dto.setBetItemId(((BigInteger) record[0]).longValue());
                dto.setLogin_time(((Timestamp) record[1]).toLocalDateTime());
                dto.setSlotNumber((Integer) record[2]);
                dto.setUserid((Integer) record[3]);
//                dto.setUserid(((BigInteger) record[3]).longValue());
                return dto;
            }).collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("raceResults", last5Results);
            response.put("betResults", last5BetResults);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("An error occurred while fetching winning horse details: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "An error occurred while processing your request"));
        }
    }

}
