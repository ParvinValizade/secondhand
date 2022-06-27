package com.company.secondhand.advertisement.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AdvertisementDto(
    val id: String,
    val title: String,
    val price: BigDecimal,
    val description: String,
    val creationDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime,
    val userId: Long,
    val hashtags: Set<String>
)
