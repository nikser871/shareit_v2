package com.shareit.item;


import com.shareit.booking.BookingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import com.shareit.booking.Booking;
import com.shareit.user.User;

@Data
@AllArgsConstructor
public class Item {

    private long id;
    private User owner;
    private String name;
    private String description;
    @Value("1")
    private boolean isAvailable;
    private BookingDto booking;
    private long countOfBookings;

}
