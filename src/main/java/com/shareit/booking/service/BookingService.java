package com.shareit.booking.service;

import com.shareit.booking.Booking;
import com.shareit.booking.BookingDto;

import java.util.Set;

public interface BookingService {
    Set<BookingDto> getBookingsByID(long ownerId);
}
