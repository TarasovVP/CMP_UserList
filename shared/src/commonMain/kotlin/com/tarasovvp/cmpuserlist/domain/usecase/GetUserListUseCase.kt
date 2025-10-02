package com.tarasovvp.cmpuserlist.domain.usecase

import com.tarasovvp.cmpuserlist.domain.model.User

interface GetUserListUseCase {
    suspend fun execute(forceRefresh: Boolean): List<User>
}