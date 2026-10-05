package shibin.kmp.userspace.domain.usecase

import shibin.kmp.userspace.domain.model.User
import shibin.kmp.userspace.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return repository.getUsers()
    }
}
