package shibin.kmp.userspace.domain.repository

import shibin.kmp.userspace.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun getUser(id: Int): User
}