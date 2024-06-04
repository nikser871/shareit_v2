package com.shareit.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import com.shareit.booking.Booking;

@Data
@AllArgsConstructor
public class ItemDto {

    private long id;
    private String name;
    private String description;
//    private Booking booking;
    @Value("true")
    private boolean isAvailable;
    @Value("0")
    private long countOfBookings;
}
