package com.example.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{ //Ticket and Booking class
    @ManyToOne
    private User user;

    private int amount;

    @ManyToMany
    private List<ShowSeat> showSeats;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    private Date bookedDate;

    @OneToMany
    private List<Payment> payments;

}

//cardinalities

//    1   -   1
//Booking - user
//      M     -   1

//    1    -  M
//Booking - ShowSeat
//     M      -     1

//    1   -    M  //bcz payment sometimes get cancelled and have to rebook ticket
//Booking  -  payment
//     1       -    1
//line 41 in booking side is 1 bcz there can be multiple ticket booking
//but in will create as only 1 booking