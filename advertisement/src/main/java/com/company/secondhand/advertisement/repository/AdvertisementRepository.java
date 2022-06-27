package com.company.secondhand.advertisement.repository;

import com.company.secondhand.advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement,String> {
}
