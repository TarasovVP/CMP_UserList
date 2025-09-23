package com.tarasovvp.cmpuserlist.data.database.datasource

import com.tarasovvp.cmpuserlist.domain.model.User

interface LocalUserDataSource {
    suspend fun getUsers(): List<User>
    suspend fun replaceAll(users: List<User>)
}