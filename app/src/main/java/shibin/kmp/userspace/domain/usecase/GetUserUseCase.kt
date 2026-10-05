package shibin.kmp.userspace.domain.usecase

import shibin.kmp.userspace.domain.model.User
import shibin.kmp.userspace.domain.repository.UserRepository

class GetUserUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: Int): User {
        return repository.getUser(id)
    }
}