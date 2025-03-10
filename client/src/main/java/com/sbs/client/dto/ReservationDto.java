package com.sbs.client.dto;

import com.sbs.client.enums.ReservationStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {

    private long id;

    private Date bookDate;

    private ReservationStatus reservationStatus;

    private String clientName;

    private String companyName;

    private String serviceName;
}
