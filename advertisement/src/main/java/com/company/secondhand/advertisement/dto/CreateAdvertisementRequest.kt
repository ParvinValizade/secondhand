package com.company.secondhand.advertisement.dto

import java.math.BigDecimal

data class CreateAdvertisementRequest(
    val title: String,
    val description: String,
    val price: BigDecimal,
    val userId: Long
)
