@file:OptIn(ExperimentalMaterial3Api::class)

package com.tarasovvp.cmpuserlist.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MyApplicationTheme {
        val viewModel: UsersViewModel = koinViewModel<UsersViewModel>()
        val state by viewModel.uiState.collectAsState()
        Content(viewModel, state)
    }
}

@Composable
fun Content(viewModel: UsersViewModel, state: UiState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Users") }
                )
            }
        ) { innerPadding ->
            when {
                state.users.isEmpty() && state.isLoading -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.error != null -> {
                    ErrorView(
                        message = state.error,
                        onRetry = { viewModel.initialize() }
                    )
                }

                else -> {
                    UsersScreen(
                        users = state.users,
                        isRefreshing = state.isLoading,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        { viewModel.initialize() }
                    )
                }
            }
        }
    }
}