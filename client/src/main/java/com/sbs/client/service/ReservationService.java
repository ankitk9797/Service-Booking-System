package com.sbs.client.service;

import com.sbs.client.dto.ReservationDto;
import com.sbs.client.dto.ReservationInputDto;
import com.sbs.client.enums.ReservationStatus;

import java.util.List;

public interface ReservationService {
    ReservationDto bookService(ReservationInputDto dto);

    List<ReservationDto> getAllBookings();

    List<ReservationDto> getBookingsByUserId(long userId);

    List<ReservationDto> getBookingsByCompanyId(long companyId);

    ReservationDto changeBookingStatus(long reservationId, ReservationStatus status);

}
