package com.tarasovvp.cmpuserlist.di

import com.tarasovvp.cmpuserlist.domain.usecase.GetUserListUseCase

suspend fun getUserListUseCase(): GetUserListUseCase = getKoin().get()