package shibin.kmp.userspace.features.users.domain.repository

import shibin.kmp.userspace.features.users.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>
}