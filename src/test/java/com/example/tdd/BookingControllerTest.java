package com.example.tdd;

import com.example.tdd.controllers.BookingController;
import com.example.tdd.model.BookingModel;
import com.example.tdd.services.BookingService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest
public class BookingControllerTest {

    @Autowired
    private BookingController controller;

    @MockBean
    private BookingService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.controller);
    }

    @Test
    public void bookingTestGetAll() throws Exception  {
        mvc.perform(MockMvcRequestBuilders
                .get("/bookings")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getBookingByIdTest() throws Exception {
        mvc.perform( MockMvcRequestBuilders
                .get("/bookings/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void bookingTestSave() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-02-07");
        LocalDate checkOut = LocalDate.parse("2022-02-17");

        mvc.perform( MockMvcRequestBuilders
                        .post("/bookings")
                        .content(asJsonString(new BookingModel("1", "João", checkIn, checkOut, 2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void bookingTestUpdate() throws Exception {
        LocalDate checkIn = LocalDate.parse("2022-02-07");
        LocalDate checkOut = LocalDate.parse("2022-02-17");

        mvc.perform(MockMvcRequestBuilders
                .put("/bookings/{id}", 2L)
                .content(asJsonString(new BookingModel("1", "João2", checkIn, checkOut, 2)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("João2"));
    }

    @Test
    public void deleteBookingTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/bookings/{id}", 1L))
                .andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
