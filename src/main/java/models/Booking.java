package models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{ //Ticket

    private User user;

    private int amount;

    private List<ShowSeat> showSeats;

    private BookingStatus bookingStatus;

    private Date bookedDate;

    private List<Payment> payments;

}
