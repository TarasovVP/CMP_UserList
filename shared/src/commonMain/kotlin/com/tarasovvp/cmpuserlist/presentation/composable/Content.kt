package com.tarasovvp.cmpuserlist.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tarasovvp.cmpuserlist.presentation.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(state: UiState, onRetry: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
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
                    onRetry = onRetry
                )
            }

            else -> {
                UsersList(
                    users = state.users
                )
            }
        }
    }
}