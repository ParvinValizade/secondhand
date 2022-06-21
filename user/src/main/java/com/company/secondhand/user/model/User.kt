package com.company.secondhand.user.model

import javax.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val mail: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?
){
    constructor() : this(
        null,
         null,
        null,
        null,
        null
    )

}

