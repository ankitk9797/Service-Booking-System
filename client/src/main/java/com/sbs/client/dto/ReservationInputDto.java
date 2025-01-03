package com.sbs.client.dto;

import com.sbs.client.enums.ReservationStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationInputDto {

    private long id;

    private Date bookDate;

    private ReservationStatus reservationStatus;

    private Long clientId;

    private Long companyId;

    private Long adId;

}
