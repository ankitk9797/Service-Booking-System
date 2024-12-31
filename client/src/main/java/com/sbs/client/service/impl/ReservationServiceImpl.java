package com.sbs.client.service.impl;

import com.sbs.client.dto.ReservationDto;
import com.sbs.client.dto.UserDto;
import com.sbs.client.entity.Reservation;
import com.sbs.client.enums.ReservationStatus;
import com.sbs.client.external.AuthService;
import com.sbs.client.external.CompanyService;
import com.sbs.client.repository.ReservationRepository;
import com.sbs.client.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ReservationDto bookService(ReservationDto dto) {

        Reservation reservation = new Reservation();
        reservation.setReservationStatus(dto.getReservationStatus());
        reservation.setBookDate(dto.getBookDate());
        reservation.setAdId(dto.getAdId());
        reservation.setClientUserId(dto.getUserId());
        reservation.setCompanyUserId(dto.getCompanyId());

        reservationRepository.save(reservation);

        return reservation.getReservationDto();
    }

    @Override
    public List<ReservationDto> getAllBookings() {
        return reservationRepository.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getBookingsByUserId(long userId) {
        return reservationRepository.findAllByClientUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
    }

    @Override
    public ReservationDto changeBookingStatus(long reservationId, ReservationStatus status) {
        Optional<Reservation> optional = reservationRepository.findById(reservationId);
        if(optional.isPresent()) {
            Reservation reservation = optional.get();
            reservation.setReservationStatus(status);

            reservationRepository.save(reservation);
            return reservation.getReservationDto();
        }
        return null;
    }
}
