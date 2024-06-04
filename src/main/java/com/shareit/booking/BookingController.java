package com.shareit.booking;


import com.shareit.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shareit.booking.repository.RepositoryBooking;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    RepositoryBooking repositoryBooking;
    MapperBooking mapperBooking;

    BookingService bookingService;

    @Autowired
    public BookingController(RepositoryBooking repositoryBooking, MapperBooking mapperBooking,
                             BookingService bookingService) {
        this.repositoryBooking = repositoryBooking;
        this.mapperBooking = mapperBooking;
        this.bookingService = bookingService;
    }

    @GetMapping
    public Set<BookingDto> getBookings(@RequestHeader("X-Later-User-Id") long id) {
        return bookingService.getBookingsByID(id);
    }

    @PostMapping(value = "book/{itemId}")
    public BookingDto bookItem(@PathVariable long itemId, @RequestHeader("X-Later-User-Id") long id, @RequestParam int day) {
        return mapperBooking.toDto(repositoryBooking.bookItem(itemId, id, day));
    }

    @PostMapping("/confirm/{itemId}/{bookingId}")
    public String confirmBook(@PathVariable long itemId, @PathVariable long bookingId,
                              @RequestHeader("X-Later-User-Id") long id) {
        return repositoryBooking.confirmBooking(itemId, bookingId, id);
    }




}
