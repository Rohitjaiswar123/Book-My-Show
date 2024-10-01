package com.example.BookMyShow.controllers;

import com.example.BookMyShow.dtos.BookMovieRequestDto;
import com.example.BookMyShow.dtos.BookMovieResponseDto;
import com.example.BookMyShow.models.Booking;
import com.example.BookMyShow.models.BookingResponseStatus;
import com.example.BookMyShow.services.BookingService;

public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

//    BookMovie method taking request as Input and Response as output
    public BookMovieResponseDto bookMovie(BookMovieRequestDto bookMovieRequestDto) {

//        creating an object of bookMovie response dto
        BookMovieResponseDto bookMovieResponseDto = new BookMovieResponseDto();

//        Booking a ticket and catching error
        try{
            Booking booking = bookingService.bookMovie(bookMovieRequestDto.getUserId(),
                    bookMovieRequestDto.getShowId(),
                    bookMovieRequestDto.getShowSeatIds());
            bookMovieResponseDto.setBookingId(booking.getId());
            bookMovieResponseDto.setAmount(booking.getAmount());
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.SUCCESS);
        } catch (Exception exception){
            bookMovieResponseDto.setBookingResponseStatus(BookingResponseStatus.FAILURE);
        }

//        This line returns the response DTO, whether the booking was successful or not.
        return bookMovieResponseDto;
    }

}


/*

Customer -> Frontend (Website OR Mobile App) -> Calls the backend API's

 */