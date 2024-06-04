package com.shareit.booking.service;

import com.shareit.booking.BookingDto;
import com.shareit.booking.MapperBooking;
import com.shareit.booking.repository.RepositoryBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{

    RepositoryBooking repositoryBooking;
    MapperBooking mapperBooking;

    @Autowired
    public BookingServiceImpl(RepositoryBooking repositoryBooking, MapperBooking mapperBooking) {
        this.repositoryBooking = repositoryBooking;
        this.mapperBooking = mapperBooking;
    }

    @Override
    public Set<BookingDto> getBookingsByID(long ownerId) {

        return repositoryBooking.getBookingsByID(ownerId)
                .stream()
                .map(x -> mapperBooking.toDto(x))
                .collect(Collectors.toSet());
    }
}
