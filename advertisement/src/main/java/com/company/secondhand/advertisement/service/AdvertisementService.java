package com.company.secondhand.advertisement.service;

import com.company.secondhand.advertisement.dto.AdvertisementDto;
import com.company.secondhand.advertisement.dto.CreateAdvertisementRequest;
import com.company.secondhand.advertisement.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request){
        return null;
    }
}
