package com.tarasovvp.cmpuserlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarasovvp.cmpuserlist.domain.usecase.GetUserListUseCase
import com.tarasovvp.cmpuserlist.presentation.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUserListUseCase: GetUserListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        initialize()
    }

    fun initialize() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            val cached =
                runCatching { getUserListUseCase.execute(forceRefresh = false) }
                    .getOrDefault(emptyList())
            _uiState.update { it.copy(isLoading = false, error = null, users = cached) }
            runCatching {
                getUserListUseCase.execute(true)
            }.onSuccess { list ->
                _uiState.update { it.copy(isLoading = false, users = list) }
            }.onFailure { e ->
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Unknown error") }
            }
        }
    }
}