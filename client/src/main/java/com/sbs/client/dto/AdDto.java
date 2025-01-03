package com.sbs.client.dto;

import org.springframework.web.multipart.MultipartFile;

public class AdDto {

    private long id;

    private String serviceName;

    private String description;

    private Double price;

    private MultipartFile img;
    private byte[] returnedImg;

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public byte[] getReturnedImg() {
        return returnedImg;
    }

    public void setReturnedImg(byte[] returnedImg) {
        this.returnedImg = returnedImg;
    }

    private Long userId;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

