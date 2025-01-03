package com.sbs.client.entity;

import com.sbs.client.dto.ReservationDto;
import com.sbs.client.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReservationStatus reservationStatus;


    private Date bookDate;

    private long clientUserId;

    private long companyUserId;

    private long adId;

    public ReservationDto getReservationDto(){
        ReservationDto dto = new ReservationDto();

        dto.setId(id);
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);

        return dto;
    }
}
