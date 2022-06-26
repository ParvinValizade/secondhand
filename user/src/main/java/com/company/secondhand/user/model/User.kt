package com.company.secondhand.user.model

import javax.persistence.*

@Entity
@Table(name = "\"user\"")
data class User(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true)
    val mail: String?,
    val firstName: String?,
    val lastName: String?,
    val middleName: String?,
    val isActive: Boolean?,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val userDetails: Set<UserDetails>?
){
    constructor() : this(
        null,
         null,
        null,
        null,
        null,
        null,
        HashSet()
    )

    constructor(mail: String, firstName: String, lastName: String, middleName: String,isActive: Boolean) : this(
        id =null,
        mail =mail,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        isActive = isActive
    )

    constructor(id: Long?, mail: String, firstName: String, lastName: String, middleName: String, isActive: Boolean) : this(
        id = id,
        mail =mail,
        firstName = firstName,
        lastName = lastName,
        middleName = middleName,
        isActive = isActive,
        HashSet()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (mail != other.mail) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (middleName != other.middleName) return false
        if (isActive != other.isActive) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (mail?.hashCode() ?: 0)
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (middleName?.hashCode() ?: 0)
        result = 31 * result + (isActive?.hashCode() ?: 0)
        return result
    }


}

