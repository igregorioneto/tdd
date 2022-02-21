package com.example.tdd.controllers;

import com.example.tdd.model.BookingModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping
    @ResponseBody
    public String getAll() {
        return "OK";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") String id) {
        return "OK";
    }

    @PostMapping
    public ResponseEntity<BookingModel> save(@RequestBody BookingModel booking) {
        return new ResponseEntity(booking, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingModel> update(@PathVariable("id") String id, @RequestBody BookingModel booking) {
        return new ResponseEntity(booking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingModel> deleteEmployee(@PathVariable("id") String id) {
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
