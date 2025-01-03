package com.sbs.company.services.impl;


import com.sbs.company.dto.AdDto;
import com.sbs.company.entity.Ad;
import com.sbs.company.repository.AdRepository;
import com.sbs.company.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    private String uploadDir = "/path/to/save/images";

    @Override
    public AdDto postAd(AdDto dto) throws IOException {
        String fileName = dto.getImg().getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        // Check if the directory exists, and create it if it doesn't
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save the file to the file system
        Path path = Paths.get(filePath);
        Files.write(path, dto.getImg().getBytes());

        // Save file metadata in the database
        Ad ad=new Ad();
        ad.setServiceName(dto.getServiceName());
        ad.setDescription(dto.getDescription());
        ad.setPrice(dto.getPrice());
        ad.setUserId(dto.getUserId());
        ad.setImgPath(filePath);

        adRepository.save(ad);

        return ad.getAdDto();
    }

    @Override
    public AdDto getAdById(long adId) {
        Optional<Ad> optional = adRepository.findById(adId);
        return optional.map(Ad::getAdDto).orElse(null);
    }

    @Override
    public AdDto updateAd(long adId, AdDto dto) throws IOException {
        Optional<Ad> optional = adRepository.findById(adId);
        if(optional.isPresent()) {
            Ad ad = optional.get();
            if (dto.getImg()!=null) {
            String fileName = dto.getImg().getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;

            // Check if the directory exists, and create it if it doesn't
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file to the file system
            Path path = Paths.get(filePath);
            Files.write(path, dto.getImg().getBytes());
                ad.setImgPath(filePath);
            }

            if(dto.getServiceName()!=null) {
                ad.setServiceName(dto.getServiceName());
            }
            if(dto.getDescription()!=null) {
                ad.setDescription(dto.getDescription());
            }
            if(dto.getPrice()!=null) {
                ad.setPrice(dto.getPrice());
            }
            if(dto.getUserId()!=null) {
                ad.setUserId(dto.getUserId());
            }


            adRepository.save(ad);


            return ad.getAdDto();
        }
        return null;
    }

    @Override
    public boolean deleteAd(long adId) {
        Optional<Ad> optional = adRepository.findById(adId);
        if(optional.isPresent()) {
            Ad ad = optional.get();
            try {
                Path path = Paths.get(ad.getImgPath());

                // Check if the file exists before attempting to delete
                if (Files.exists(path)) {
                    Files.delete(path);
                    System.out.println("File deleted successfully.");
                } else {
                    System.out.println("File does not exist.");
                }
            } catch (IOException e) {
                System.err.println("Error deleting file: " + e.getMessage());
            }
          adRepository.deleteById(adId);
          return true;
        }
        return false;
    }

    @Override
    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    @Override
    public List<AdDto> getAllAdsByUserID(long userId) {
        return adRepository.findAllByUserId(userId).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

    @Override
    public List<AdDto> serachAdByServiceName(String serviceName) {
        return adRepository.findAllByServiceNameContaining(serviceName).stream().map(Ad::getAdDto).collect(Collectors.toList());
    }

}
