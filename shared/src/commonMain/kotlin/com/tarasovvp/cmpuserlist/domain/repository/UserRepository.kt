package com.tarasovvp.cmpuserlist.domain.repository

import com.tarasovvp.cmpuserlist.domain.model.User

interface UserRepository {
    suspend fun getUsers(forceRefresh: Boolean = false): List<User>
}