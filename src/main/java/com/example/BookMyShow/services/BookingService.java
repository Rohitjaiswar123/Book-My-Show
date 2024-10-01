package com.example.BookMyShow.services;


import com.example.BookMyShow.exceptions.InvalidShowException;
import com.example.BookMyShow.exceptions.InvalidUserException;
import com.example.BookMyShow.exceptions.ShowSeatNotAvailableException;
import com.example.BookMyShow.models.*;
import com.example.BookMyShow.repositories.ShowRepository;
import com.example.BookMyShow.repositories.ShowSeatRepository;
import com.example.BookMyShow.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Isolation;


import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingService {

    private UserRepository userRepository;

    private ShowRepository showRepository;

    private ShowSeatRepository showSeatRepository;

    public BookingService(UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          ShowRepository showRepository){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId , Long showId, List<Long> showSeatIds) {
        //Booking bcz many controller can interact with this controller
        /*
        STEPS to book movie tickets :-
        -------ACQUIRE THE LOCK-------

        1. Get the User from userId from DB.
        2. Get the Show from showId from DB.
        3. Get the list of showSeats from showSeatIds from DB.
        4. Check if all the seats are available or not.
        5. If not, throw an exception. -> Code
        6. If yes, Mark the status as BLOCKED.
        7. Save the status of seats in the DB as well.
        8. Create the BOOKING object.
        9. Save the booking to DB.
        10. Return the Booking.

        -------RELEASE THE LOCK-------
         */

//        1. Get the user from userId from DB
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("Invalid UserId");
        }
        User bookedBy = optionalUser.get();


//        2. Get the Show from showId from DB.
        Optional<Show> optionalShow = showRepository.findById(showId);
        if (optionalShow.isEmpty()) {
            throw new InvalidShowException("Invalid showId");
        }
        Show bookedShow = optionalShow.get();

//        3. Get the list of showSeats from showSeatIds from DB.
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // 4. Check if all the seats are available or not.
        // 5. If not, throw an exception.
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowSeatNotAvailableException("ShowSeat with id: " + showSeat.getId() + "isn't available.");
            }
        }

//        6.If yes, Mark the status as BLOCKED.
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);

            // 7. save the status of seats in the DB as well
            showSeatRepository.save(showSeat);
//            accessing showSeat-repository save method & then saving inside showseat blocked status

//            8. Create the BOOKING object.
            Booking booking = new Booking();
            booking.setBookingStatus(BookingStatus.IN_PROGRESS);
            booking.setUser(bookedBy);
            booking.setPayments(new ArrayList<>());
            booking.setBookedDate(new Date());
            booking.setCreatedAt(new Date());
            booking.setShowSeats(showSeats);
            booking.setAmount();//Pending will code later
        }
        return null;
    }
}
