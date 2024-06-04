package com.shareit.booking.repository;

import com.shareit.booking.Booking;
import com.shareit.booking.BookingDto;
import com.shareit.booking.MapperBooking;
import com.shareit.exception.AppException;
import org.openjdk.jol.vm.VM;
import org.springframework.beans.factory.annotation.Autowired;
import com.shareit.item.Item;
import com.shareit.item.repository.RepositoryItem;
import com.shareit.user.repository.RepositoryUser;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.time.LocalDate.now;


@Component
public class RepoBookingImpl implements RepositoryBooking {

    private static int ID = 1;
    // Long -- itemId
    public Map<Long, HashSet<Booking>> itemMapBookings = new HashMap<>();
    public Map<Long, Booking> bookingMap = new HashMap<>();

    public Map<Long, HashSet<Booking>> ownerMapBookings = new HashMap<>();


    RepositoryUser repositoryUser;

    RepositoryItem repositoryItem;
    MapperBooking mapperBooking;

    @Autowired
    public RepoBookingImpl(RepositoryItem repositoryItem, RepositoryUser repositoryUser,
                           MapperBooking mapperBooking) {
        this.repositoryItem = repositoryItem;
        this.repositoryUser = repositoryUser;
        this.mapperBooking = mapperBooking;
    }


    @Override
    public Set<Booking> getBookingsByID(long ownerId) {
        return ownerMapBookings.get(ownerId);
    }

    @Override
    public Booking bookItem(long itemId, long userId, int days) {

        Booking booking = Booking.builder()
                .id(ID)
                .itemId(itemId)
                .customer(repositoryUser.getUserById(userId))
                .isConfirmed(false)
                .bookingDateStart(now())
                .bookingDateEnd(now().plusDays(days))
                .item(repositoryItem.getItemById(itemId))
                .build();

        itemMapBookings.putIfAbsent(booking.getItemId(), new HashSet<>());
        itemMapBookings.get(booking.getItemId()).add(booking);
        bookingMap.put(booking.getId(), booking);
        ownerMapBookings.putIfAbsent(booking.getItem().getOwner().getId(), new HashSet<>());
        ownerMapBookings.get(booking.getItem().getOwner().getId()).add(booking);

        ID++;

//        System.out.println("1 :  " + VM.current().addressOf(booking));
        return booking;
    }

    @Override
    public String confirmBooking(long itemId, long bookingId, long ownerId)  {

        Item item = repositoryItem.getItemById(itemId);
        Booking booking = bookingMap.get(bookingId);

        if (item.getOwner().getId() != ownerId) throw new AppException("You are not an owner!");

        if (!itemMapBookings.containsKey(item.getId())
                || !itemMapBookings.get(item.getId()).contains(booking)) throw new AppException("ASA");

        item.setAvailable(false);
        item.setBooking(mapperBooking.toDto(booking));

        itemMapBookings.get(item.getId()).remove(booking);
        ownerMapBookings.get(ownerId).removeIf(x -> x.getId() == bookingId);
        bookingMap.remove(bookingId);

        repositoryUser.addUsedItem(booking.getCustomer().getId(), item);
        booking.setConfirmed(true);

        return "Well done!!!";
    }


}
