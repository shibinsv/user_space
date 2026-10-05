package shibin.kmp.userspace.features.users.data.repository

import shibin.kmp.userspace.features.users.data.datasource.UserDataSource
import shibin.kmp.userspace.features.users.domain.model.User
import shibin.kmp.userspace.features.users.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return userDataSource.getUsers()
    }
}