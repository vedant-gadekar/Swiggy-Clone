package com.example.swiggyy.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.swiggyy.R
import com.example.swiggyy.feature_food.state.UiState

/**
 * A composable that handles different UI states (Loading, Success, Error, Empty) and displays
 * the appropriate UI for each state.
 *
 * @param state The UiState to observe
 * @param onRetry Callback for retry action (used in error state)
 * @param content The content to display when in success state
 * @param loadingContent Optional custom loading UI (defaults to a centered CircularProgressIndicator)
 * @param errorContent Optional custom error UI (defaults to an error message with retry button)
 * @param emptyContent Optional custom empty state UI (defaults to a message indicating no data)
 */
@Composable
fun <T> StateHandler(
    state: UiState<T>,
    onRetry: (() -> Unit)? = null,
    content: @Composable (T) -> Unit,
    loadingContent: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    },
    errorContent: @Composable (String, (() -> Unit)?) -> Unit = { message, retryAction ->
        ErrorState(
            message = message,
            onRetry = retryAction,
            modifier = Modifier.fillMaxSize()
        )
    },
    emptyContent: @Composable () -> Unit = {
        EmptyState(
            message = stringResource(R.string.no_data_available),
            modifier = Modifier.fillMaxSize()
        )
    }
) {
    when (state) {
        is UiState.Loading -> loadingContent()
        is UiState.Success -> {
            val data = state.data
            if (data is Collection<*> && data.isEmpty()) {
                emptyContent()
            } else {
                content(data)
            }
        }
        is UiState.Error -> errorContent(state.message, onRetry)
        is UiState.Empty -> emptyContent()
    }
}

/**
 * A composable that displays an error state with an optional retry button.
 *
 * @param message The error message to display
 * @param onRetry Optional callback when the retry button is clicked
 * @param modifier Modifier to be applied to the layout
 */
@Composable
fun ErrorState(
    message: String,
    onRetry: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Error,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        onRetry?.let { retry ->
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = retry) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}

/**
 * A composable that displays an empty state with an optional message.
 *
 * @param message The message to display in the empty state
 * @param modifier Modifier to be applied to the layout
 */
@Composable
fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Inbox,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )
    }
}
