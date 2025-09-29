package com.tarasovvp.cmpuserlist.presentation.state

import com.tarasovvp.cmpuserlist.domain.model.User

data class UiState(
    val isLoading: Boolean = true,
    val users: List<User> = emptyList(),
    val error: String? = null
)