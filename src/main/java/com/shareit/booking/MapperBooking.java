package com.shareit.booking;

import com.shareit.item.MapperItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperBooking {

    MapperItem mapperItem;

    @Autowired
    public MapperBooking(MapperItem mapperItem) {
        this.mapperItem = mapperItem;
    }

    public BookingDto toDto(Booking booking) {
        return new BookingDto(booking.getId(), booking.getId(), mapperItem.toDto(booking.getItem()),
                booking.getBookingDateStart(), booking.getBookingDateEnd());
    }

}
