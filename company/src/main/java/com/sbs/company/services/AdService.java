package com.sbs.company.services;

import com.sbs.company.dto.AdDto;
import com.sbs.company.entity.Ad;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface AdService {
    AdDto postAd(AdDto dto) throws IOException;

    AdDto getAdById(long adId);

    AdDto updateAd(long adId, AdDto dto) throws IOException;

    boolean deleteAd(long adId);

    List<AdDto> getAllAds();

    List<AdDto> getAllAdsByUserID(long userId);

    List<AdDto> serachAdByServiceName(String serviceName);
}
