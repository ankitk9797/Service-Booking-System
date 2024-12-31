package com.sbs.client.external;

import com.sbs.client.dto.AdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY")
public interface CompanyService {

    @GetMapping(path = "/company/ad/{adId}")
    AdDto getAd(@PathVariable long adId);
}
