package com.example.BookMyShow.repositories;

import com.example.BookMyShow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);
//    find all the showSeats by showSeatIds

    ShowSeat save(ShowSeat showSeat);
    //save --> update + insert (upsert)
    // If the showSeat object that we are passing in the input, isn't there in DB.
    //then insert it in the DB.
    // if ShowSeat object is present in the DB, then update the object in the DB.
}
