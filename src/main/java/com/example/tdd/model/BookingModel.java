package com.example.tdd.model;

import java.time.LocalDate;
import java.util.Date;

public class BookingModel {

    private String id;
    private String reserveName;
    private Date checkIn;
    private Date checkOut;
    private int numberGuests;

    public BookingModel(String id, String reserveName, Date checkIn, Date checkOut, int numberGuests) {
        this.id = id;
        this.reserveName = reserveName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuests = numberGuests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumberGuests() {
        return numberGuests;
    }

    public void setNumberGuests(int numberGuests) {
        this.numberGuests = numberGuests;
    }
}
