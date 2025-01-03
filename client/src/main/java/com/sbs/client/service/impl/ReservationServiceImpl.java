package com.sbs.client.service.impl;

import com.sbs.client.dto.AdDto;
import com.sbs.client.dto.ReservationDto;
import com.sbs.client.dto.ReservationInputDto;
import com.sbs.client.dto.UserDto;
import com.sbs.client.entity.Reservation;
import com.sbs.client.enums.ReservationStatus;
import com.sbs.client.external.AuthService;
import com.sbs.client.external.CompanyService;
import com.sbs.client.repository.ReservationRepository;
import com.sbs.client.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CompanyService companyService;

    @Override
    public ReservationDto bookService(ReservationInputDto dto) {

        Reservation reservation = new Reservation();
        reservation.setReservationStatus(dto.getReservationStatus());
        reservation.setBookDate(dto.getBookDate());
        reservation.setAdId(dto.getAdId());
        reservation.setClientUserId(dto.getClientId());
        reservation.setCompanyUserId(dto.getCompanyId());

        reservationRepository.save(reservation);

        return setReservationDetails(reservation);
    }

    @Override
    public List<ReservationDto> getAllBookings() {
        return reservationRepository.findAll().stream().map(this::setReservationDetails).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getBookingsByUserId(long userId) {
        return reservationRepository.findAllByClientUserId(userId).stream().map(this::setReservationDetails).collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getBookingsByCompanyId(long companyId) {
        return reservationRepository.findAllByCompanyUserId(companyId).stream().map(this::setReservationDetails).collect(Collectors.toList());
    }

    @Override
    public ReservationDto changeBookingStatus(long reservationId, ReservationStatus status) {
        Optional<Reservation> optional = reservationRepository.findById(reservationId);
        if(optional.isPresent()) {
            Reservation reservation = optional.get();
            reservation.setReservationStatus(status);

            reservationRepository.save(reservation);
            return setReservationDetails(reservation);
        }
        return null;
    }

    private ReservationDto setReservationDetails(Reservation reservation) {
        UserDto clientDto = authService.getUser(reservation.getClientUserId());
        UserDto companyDto = authService.getUser(reservation.getCompanyUserId());
        AdDto adDto = companyService.getAd(reservation.getAdId());

        ReservationDto reservationDto = reservation.getReservationDto();
        reservationDto.setClientName(clientDto.getName());
        reservationDto.setServiceName(adDto.getServiceName());
        reservationDto.setCompanyName(companyDto.getName());
        return reservationDto;
    }
}
