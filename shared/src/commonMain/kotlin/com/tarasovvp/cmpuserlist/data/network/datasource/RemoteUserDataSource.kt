package com.tarasovvp.cmpuserlist.data.network.datasource

import com.tarasovvp.cmpuserlist.data.network.model.RemoteUser

interface RemoteUserDataSource {
    suspend fun fetchUsers(): List<RemoteUser>
}