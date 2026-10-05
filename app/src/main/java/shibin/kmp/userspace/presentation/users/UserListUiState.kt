package shibin.kmp.userspace.presentation.users

import shibin.kmp.userspace.domain.model.User

sealed interface UserListUiState {

    data object Loading : UserListUiState

    data class Success(
        val users: List<User>
    ) : UserListUiState

    data class Error(
        val message: String
    ) : UserListUiState
}