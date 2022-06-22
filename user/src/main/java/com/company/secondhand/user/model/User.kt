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
    val middleName: String?,
    val isActive: Boolean?
){
    constructor() : this(
        null,
         null,
        null,
        null,
        null,
        null
    )

    constructor(id: Long?,mail: String, firstName: String, lastName: String, middleName: String) : this(
        id =id,
        mail =mail,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        null
    )


}

