package com.company.secondhand.advertisement.dto;

import com.company.secondhand.advertisement.model.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdvertisementDtoConverter {

    AdvertisementDtoConverter INSTANCE = Mappers.getMapper(AdvertisementDtoConverter.class);

    AdvertisementDto convertDto(Advertisement advertisement);
}
