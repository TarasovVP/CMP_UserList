@file:OptIn(ExperimentalMaterial3Api::class)

package com.tarasovvp.cmpuserlist.presentation.composable

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tarasovvp.cmpuserlist.presentation.viewmodel.UsersViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    AppTheme {
        val viewModel: UsersViewModel = koinViewModel<UsersViewModel>()
        val state by viewModel.uiState.collectAsState()
        Content(viewModel, state)
    }
}
