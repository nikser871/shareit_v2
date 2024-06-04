package com.shareit.booking;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import com.shareit.item.Item;
import com.shareit.user.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class Booking {

     // ????
    private long id;
    private long itemId;

    private User customer;
    private Item item;
    private LocalDate bookingDateStart;
    private LocalDate bookingDateEnd;
    @Value("0") // ???????????
    private boolean isConfirmed;


}
