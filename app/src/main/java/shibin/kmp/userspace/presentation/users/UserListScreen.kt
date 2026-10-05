package shibin.kmp.userspace.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import shibin.kmp.userspace.domain.model.User

@Composable
fun UserListScreen(
    viewModel: UserViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {

        UserListUiState.Loading -> {
            CircularProgressIndicator()
        }

        is UserListUiState.Success -> {
            UserListContent(
                users = state.users
            )
        }

        is UserListUiState.Error -> {
            Text(
                text = state.message
            )
        }
    }
}

@Composable
private fun UserListContent(
    users: List<User>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = users, key = { it.id }) { user ->

            UserItem(user = user)
        }
    }
}

@Composable
private fun UserItem(
    user: User
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = user.name, style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = user.email, style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = user.city, style = MaterialTheme.typography.bodySmall
            )
        }

    }
}