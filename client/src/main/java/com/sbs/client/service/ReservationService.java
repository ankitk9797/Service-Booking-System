package com.sbs.client.service;

import com.sbs.client.dto.ReservationDto;
import com.sbs.client.entity.Reservation;
import com.sbs.client.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {
    ReservationDto bookService(ReservationDto dto);

    List<ReservationDto> getAllBookings();

    List<ReservationDto> getBookingsByUserId(long userId);

    ReservationDto changeBookingStatus(long reservationId, ReservationStatus status);

}
