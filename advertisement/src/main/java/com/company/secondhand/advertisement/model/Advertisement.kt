package com.company.secondhand.advertisement.model

import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "advertisement")
data class Advertisement(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,
    val title: String?,
    val price: BigDecimal?,
    val description: String?,
    val creationDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime,

    val userId: Long?,
    val hashtags: Set<String> = HashSet()
){
    constructor(
        title: String,
        price: BigDecimal,
        description: String,
        creationDate: LocalDateTime,
        lastModifiedDate: LocalDateTime,
        userId: Long,
        hashtags: Set<String>) : this(

        null,
        title = title,
        price = price,
        description = description,
        creationDate = creationDate,
        lastModifiedDate = lastModifiedDate,
        userId = userId,
        hashtags = hashtags
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Advertisement

        if (id != other.id) return false
        if (title != other.title) return false
        if (price != other.price) return false
        if (description != other.description) return false
        if (creationDate != other.creationDate) return false
        if (lastModifiedDate != other.lastModifiedDate) return false
        if (userId != other.userId) return false
        if (hashtags != other.hashtags) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + creationDate.hashCode()
        result = 31 * result + lastModifiedDate.hashCode()
        result = 31 * result + (userId?.hashCode() ?: 0)
        result = 31 * result + hashtags.hashCode()
        return result
    }


}
