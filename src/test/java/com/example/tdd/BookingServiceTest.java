package com.example.tdd;

import com.example.tdd.model.BookingModel;
import com.example.tdd.repositories.BookingRepository;
import com.example.tdd.services.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration {

        @Bean
        public BookingService bookingService() {
            return new BookingService();
        }

    }

    @Autowired
    BookingService service;

    @MockBean
    BookingRepository repository;

    @Test
    public void bookingTestServiceDaysCalculator() {
        String name = "João";
        int days = service.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Before
    public void setup() {
        LocalDate checkIn = LocalDate.parse("2022-02-07");
        LocalDate checkOut = LocalDate.parse("2022-02-17");

        BookingModel booking = new BookingModel("1", "João", checkIn, checkOut, 2);

        Mockito.when(repository.findByReserveName(booking.getReserveName()))
                .thenReturn(Optional.of(booking));
    }
}
