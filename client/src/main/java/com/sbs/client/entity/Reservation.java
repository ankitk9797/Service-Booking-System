package com.sbs.client.entity;

import com.sbs.client.dto.ReservationDto;
import com.sbs.client.enums.ReservationStatus;
import com.sbs.client.enums.ReviewStatus;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public long getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(long clientUserId) {
        this.clientUserId = clientUserId;
    }

    public long getCompanyUserId() {
        return companyUserId;
    }

    public void setCompanyUserId(long companyUserId) {
        this.companyUserId = companyUserId;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public ReservationDto getReservationDto(){
        ReservationDto dto = new ReservationDto();

        dto.setId(id);
        dto.setBookDate(bookDate);
        dto.setReservationStatus(reservationStatus);

        dto.setAdId(adId);
        dto.setCompanyId(companyUserId);
        dto.setUserId(clientUserId);

        return dto;
    }
}
