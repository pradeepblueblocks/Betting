//package com.bet.BettingGame.controller;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.bet.BettingGame.model.Game;
//import com.bet.BettingGame.model.Slot;
//import com.bet.BettingGame.model.SlotRequest;
//import com.bet.BettingGame.repository.SlotDetailsRepository;
//import com.bet.BettingGame.repository.SlotRepository;
//import com.bet.BettingGame.service.SlotDetailsService;
//import com.bet.BettingGame.service.SlotService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
////import org.mockito.MockBean;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.sql.Time;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@WebMvcTest(AdminController.class)
//@ExtendWith(MockitoExtension.class)
//public class AdminControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SlotService slotService;
//
//    @MockBean
//    private SlotDetailsService slotDetailsService;
//
//    @MockBean
//    private SlotRepository slotRepository;
//
//    @MockBean
//    private SlotDetailsRepository slotDetailsRepository;
//
//    @InjectMocks
//    private AdminController adminController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//    }
//
//    @Test
//    public void testCreateSlot() throws Exception {
//        SlotRequest request = new SlotRequest();
//        request.setSlotDurationInMinutes(60);
//        request.setAdminStartTime(LocalTime.of(10, 0));
//        request.setAdminEndTime(LocalTime.of(12, 0));
//        request.setGameId(1L);
//        request.setAdminId(1);
//        request.setSelectedDate(LocalDate.now());
//        request.setWinningPrice(100.0f);
//
//        when(slotService.checkForSlotOverlap(any(Long.class), any(LocalDate.class), any(LocalTime.class), any(LocalTime.class)))
//                .thenReturn(false);
//        when(slotDetailsService.findMaxSlotNumberByAdmin(any(Integer.class)))
//                .thenReturn(1);
//        when(slotService.findIdByAdminStartTimeAndAdminEndTimeAndGameId(any(LocalTime.class), any(LocalTime.class), any(Long.class), any(LocalDate.class)))
//                .thenReturn(1L);
//
//        mockMvc.perform(post("/api/slots")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetUpcomingSlots() throws Exception {
//        List<Object[]> upcomingSlots = new ArrayList<>();
//        upcomingSlots.add(new Object[]{1L, Time.valueOf("10:00:00"), Time.valueOf("11:00:00"), 3600, 1L, "GameName"});
//
//        when(slotRepository.findAdminSlotsForCurrentDate(any(LocalDate.class), any(LocalTime.class)))
//                .thenReturn(upcomingSlots);
//
//        mockMvc.perform(get("/api/adminUpcomingSlots")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetSlotDetails() throws Exception {
//        Slot slot = new Slot();
//        slot.setSlotAdminId(1L);
//        slot.setAdminStartTime(LocalTime.of(10, 0));
//        slot.setAdminEndTime(LocalTime.of(11, 0));
//        slot.setSlotDurationInSeconds(3600);
//        slot.setGameId(1L);
//
//        Game game = new Game();
//        game.setGameName("GameName");
//
//        slot.setGame(game);
//
//        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));
//
//        mockMvc.perform(get("/api/getSlot/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateSlot() throws Exception {
//        Slot slot = new Slot();
//        slot.setSlotAdminId(1L);
//        slot.setAdminStartTime(LocalTime.of(15, 30));
//        slot.setAdminEndTime(LocalTime.of(16, 0));
//        slot.setSlotDurationInSeconds(3600);
//        slot.setGameId(1L);
//        slot.setSelectedDate(LocalDate.now());
//
//        SlotRequest request = new SlotRequest();
//        request.setAdminStartTime(LocalTime.of(15, 0));
//        request.setAdminEndTime(LocalTime.of(16, 0));
//        request.setSlotDurationInMinutes(30);
//        request.setAdminId(1);
//
//        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));
//        when(slotDetailsService.findMaxSlotNumberByAdmin(any(Integer.class)))
//                .thenReturn(1);
//
//        mockMvc.perform(put("/api/updateSlot/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().isOk());
//    }
//}
