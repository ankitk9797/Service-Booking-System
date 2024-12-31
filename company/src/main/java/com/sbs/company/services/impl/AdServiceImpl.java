package com.sbs.company.services.impl;


import com.sbs.company.dto.AdDto;
import com.sbs.company.entity.Ad;
import com.sbs.company.repository.AdRepository;
import com.sbs.company.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Override
    public AdDto postAd(AdDto dto) {
        Ad ad = new Ad();
        ad.setServiceName(dto.getServiceName());
        ad.setDescription(dto.getDescription());
//        ad.setImg(dto.getImg().getBytes());
        ad.setPrice(dto.getPrice());
        ad.setUserId(dto.getUserId());

        adRepository.save(ad);

        return ad.getAdDto();
    }

    @Override
    public AdDto getAdById(long adId) {
        Optional<Ad> optional = adRepository.findById(adId);
        if(optional.isPresent()){
            return optional.get().getAdDto();
        }
        return null;
    }

    @Override
    public AdDto updateAd(long adId, AdDto dto) {
        AdDto adDto = getAdById(adId);
        if(adDto!=null) {
            Ad ad = new Ad();
            ad.setId(adDto.getId());
            ad.setServiceName(dto.getServiceName());
            ad.setPrice(dto.getPrice());
//            ad.setImg(dto.getImg().getBytes());
            ad.setDescription(dto.getDescription());
            ad.setUserId(dto.getUserId());

            adRepository.save(ad);

            return ad.getAdDto();
        }
        return null;
    }

    @Override
    public boolean deleteAd(long adId) {
        AdDto adDto = getAdById(adId);
        if(adDto !=null) {
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
