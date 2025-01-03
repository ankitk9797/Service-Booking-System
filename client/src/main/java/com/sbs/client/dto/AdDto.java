package com.sbs.client.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDto {

    private long id;

    private String serviceName;

    private String description;

    private Double price;

    private MultipartFile img;

    private byte[] returnedImg;

}

