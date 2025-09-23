package com.tarasovvp.cmpuserlist.data.repository

import com.tarasovvp.cmpuserlist.data.database.datasource.LocalUserDataSource
import com.tarasovvp.cmpuserlist.data.mapper.toDomain
import com.tarasovvp.cmpuserlist.data.network.datasource.RemoteUserDataSource
import com.tarasovvp.cmpuserlist.domain.model.User
import com.tarasovvp.cmpuserlist.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remote: RemoteUserDataSource,
    private val local: LocalUserDataSource
) : UserRepository {
    override suspend fun getUsers(forceRefresh: Boolean): List<User> {
        val cached = local.getUsers()
        if (cached.isNotEmpty() && !forceRefresh) return cached
        val fresh = remote.fetchUsers().map { it.toDomain() }
        local.replaceAll(fresh)
        return fresh
    }
}