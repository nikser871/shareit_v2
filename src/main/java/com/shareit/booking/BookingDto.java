package com.shareit.booking;


import com.shareit.item.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingDto {
    private long id;
    private long itemId;
    private ItemDto itemDto;
    private LocalDate bookingDateStart;
    private LocalDate bookingDateEnd;
}
