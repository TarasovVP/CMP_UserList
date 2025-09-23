package com.tarasovvp.cmpuserlist.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(val users: List<RemoteUser>)