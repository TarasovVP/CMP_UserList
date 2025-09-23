package com.tarasovvp.cmpuserlist.data.network.datasource

import com.tarasovvp.cmpuserlist.Constants
import com.tarasovvp.cmpuserlist.data.network.model.RemoteUser
import com.tarasovvp.cmpuserlist.data.network.model.UsersResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteUserDataSourceImpl(private val client: HttpClient) : RemoteUserDataSource {
    override suspend fun fetchUsers(): List<RemoteUser> =
        client.get("${Constants.BASE_URL}${Constants.USERS_ENDPOINT}").body<UsersResponse>().users
}