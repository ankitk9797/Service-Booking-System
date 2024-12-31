package com.sbs.company.controller;

import com.sbs.company.dto.AdDto;
import com.sbs.company.feignClient.AuthService;
import com.sbs.company.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/company")
public class AdController {

    @Autowired
    private AdService adService;

    @Autowired
    private AuthService authService;

    @PostMapping("/create-ad")
    public ResponseEntity<AdDto> postAd(@RequestBody AdDto dto) throws IOException {

       AdDto adDto = adService.postAd(dto);
       return ResponseEntity.ok().body(adDto);
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<AdDto> getAd(@PathVariable long adId) {

        AdDto adDto = adService.getAdById(adId);
        if(adDto!=null) {
            return ResponseEntity.ok().body(adDto);
        }

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/ad/{adId}")
    public ResponseEntity<AdDto> updateAd(@PathVariable long adId, @RequestBody AdDto dto) {

        AdDto adDto = adService.getAdById(adId);
        if(adDto!=null){
            return ResponseEntity.ok().body(adDto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/ad/{adId}")
    public ResponseEntity<Boolean> deleteAd(@PathVariable long adId) {

        boolean isDeleted = adService.deleteAd(adId);
        if(isDeleted){
            return ResponseEntity.ok().body(true);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(path = "/allAds")
    public ResponseEntity<List<AdDto>> getAllAds() {

        return ResponseEntity.ok().body(adService.getAllAds());
    }

    @GetMapping(path = "/allAds/{userId}")
    public ResponseEntity<List<AdDto>> getAllAdsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok().body(adService.getAllAdsByUserID(userId));
    }

    @GetMapping(path = "/search/{serviceName}")
    public ResponseEntity<List<AdDto>> searchByServiceName(@PathVariable String serviceName) {
        return ResponseEntity.ok().body(adService.serachAdByServiceName(serviceName));
    }

}
