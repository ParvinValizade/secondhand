package com.company.secondhand.user.model

import javax.persistence.*

@Entity
data class UserDetails(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val phoneNumber: String?,
    val address: String?,
    val city: String?,
    val country: String?,
    val postCode: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User?
){
    constructor(phoneNumber: String?,address: String?,city: String?,country: String?,postCode: String?,user: User?) : this(
        null,
        phoneNumber = phoneNumber,
        address = address,
        city = city,
        country = country,
        postCode = postCode,
        user = user
    )

    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDetails

        if (id != other.id) return false
        if (phoneNumber != other.phoneNumber) return false
        if (address != other.address) return false
        if (city != other.city) return false
        if (country != other.country) return false
        if (postCode != other.postCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (postCode?.hashCode() ?: 0)
        return result
    }


}



