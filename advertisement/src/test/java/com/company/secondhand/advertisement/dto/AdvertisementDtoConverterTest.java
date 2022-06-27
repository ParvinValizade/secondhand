package com.company.secondhand.advertisement.dto;

import com.company.secondhand.advertisement.model.Advertisement;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdvertisementDtoConverterTest {

    @Test
    void  testConvert(){
        Advertisement advertisement = new Advertisement(
                "ad-id",
                "title",
                new BigDecimal(123),
                "description1",
                LocalDateTime.now(),
                LocalDateTime.now(),
                100L,
                Set.of("hashtag1","hashtag2")
        );

        AdvertisementDto advertisementDto = AdvertisementDtoConverter.INSTANCE.convertDto(advertisement);

        assertEquals(advertisementDto.getTitle(),"title");
        assertEquals(advertisementDto.getDescription(),"description1");
        assertEquals(advertisementDto.getPrice(),new BigDecimal(123));
        assertEquals(advertisementDto.getUserId(),100L);
        assertEquals(advertisementDto.getHashtags(),Set.of("hashtag1","hashtag2"));

    }

}
