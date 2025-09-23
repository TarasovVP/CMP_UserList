package com.tarasovvp.cmpuserlist.data.mapper


import com.tarasovv.cmpuserlist.UserEntity
import com.tarasovvp.cmpuserlist.data.network.model.RemoteUser
import com.tarasovvp.cmpuserlist.domain.model.User

fun RemoteUser.toDomain() = User(
    firstName = firstName,
    lastName = lastName,
    maidenName = maidenName,
    age = age,
    gender = gender,
    email = email,
    phone = phone,
    birthDate = birthDate,
    image = image
)

fun UserEntity.toDomain() = User(
    firstName = firstName,
    lastName = lastName,
    maidenName = maidenName,
    age = age.toInt(),
    gender = gender,
    email = email,
    phone = phone,
    birthDate = birthDate,
    image = image
)

fun User.toEntity() = UserEntity(
    email = email,
    firstName = firstName,
    lastName = lastName,
    maidenName = maidenName,
    age = age.toLong(),
    gender = gender,
    phone = phone,
    birthDate = birthDate,
    image = image
)