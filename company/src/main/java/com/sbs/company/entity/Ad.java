package com.sbs.company.entity;

import com.sbs.company.dto.AdDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Entity
@Table(name = "ads")
@Data
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String serviceName;

    private String description;

    private Double price;

    private long userId;

    private String imgPath;

    public AdDto getAdDto(){
        AdDto adDTO = new AdDto();

        adDTO.setId(id);
        adDTO.setServiceName(serviceName);
        adDTO.setDescription(description);
        adDTO.setPrice(price);
        adDTO.setUserId(userId);

        Path path = Paths.get(imgPath);
        try {
            adDTO.setReturnedImg(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return adDTO;
    }
}
