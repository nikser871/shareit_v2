package com.shareit.booking.repository;

import com.shareit.booking.Booking;
import com.shareit.booking.BookingDto;

import java.util.*;

public interface RepositoryBooking {
    Set<Booking> getBookingsByID(long ownerId);
    Booking bookItem(long itemId, long userId, int days);
    String confirmBooking(long itemId, long bookingId, long ownerId);


}
