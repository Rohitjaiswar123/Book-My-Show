package com.example.BookMyShow.dtos;

import com.example.BookMyShow.models.BookingResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {

    private Long bookingId;
    private int amount;
    private BookingResponseStatus bookingResponseStatus;
}
