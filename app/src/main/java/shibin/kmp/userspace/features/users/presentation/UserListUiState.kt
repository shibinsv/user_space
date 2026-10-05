package shibin.kmp.userspace.features.users.presentation

import shibin.kmp.userspace.features.users.domain.model.User

sealed interface UserListUiState {

    data object Loading : UserListUiState

    data class Success(
        val users: List<User>
    ) : UserListUiState

    data class Error(
        val message: String
    ) : UserListUiState
}
