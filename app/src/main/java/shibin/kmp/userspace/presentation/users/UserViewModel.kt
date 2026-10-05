package shibin.kmp.userspace.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shibin.kmp.userspace.domain.usecase.GetUsersUseCase
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)

    val uiState: StateFlow<UserListUiState> = _uiState.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UserListUiState.Loading
            try {
                val users = getUsersUseCase()
                _uiState.value = UserListUiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UserListUiState.Error(
                    e.message ?: "Something went wrong"
                )
            }
        }
    }
}