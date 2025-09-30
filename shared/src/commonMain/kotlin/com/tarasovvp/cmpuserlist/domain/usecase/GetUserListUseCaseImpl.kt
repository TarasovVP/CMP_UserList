package com.tarasovvp.cmpuserlist.domain.usecase

import com.tarasovvp.cmpuserlist.domain.model.User
import com.tarasovvp.cmpuserlist.domain.repository.UserRepository

class GetUserListUseCaseImpl(private val repository: UserRepository) : GetUserListUseCase {
    override suspend fun execute(): List<User> = repository.getUsers(false)
}