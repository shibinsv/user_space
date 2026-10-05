package shibin.kmp.userspace.features.users.domain.usecase

import shibin.kmp.userspace.features.users.domain.model.User
import shibin.kmp.userspace.features.users.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}