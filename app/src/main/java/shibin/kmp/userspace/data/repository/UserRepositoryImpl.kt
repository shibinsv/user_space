package shibin.kmp.userspace.data.repository

import shibin.kmp.userspace.data.datasource.UserDataSource
import shibin.kmp.userspace.domain.model.User
import shibin.kmp.userspace.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return dataSource.getUsers()
    }

    override suspend fun getUser(id: Int): User {
        return dataSource.getUser(id)
    }
}