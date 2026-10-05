package shibin.kmp.userspace.features.users.data.datasource

import shibin.kmp.userspace.features.users.domain.model.User
import javax.inject.Inject

class UserDataSource @Inject constructor() {

    suspend fun getUsers(): List<User> {
        return listOf(
            User(
                id = 1,
                name = "John Doe",
                email = "john@gmail.com",
                city = "Chennai"
            ),
            User(
                id = 2,
                name = "Sarah Smith",
                email = "sarah@gmail.com",
                city = "Bangalore"
            ),
            User(
                id = 3,
                name = "Alex Johnson",
                email = "alex@gmail.com",
                city = "Mumbai"
            )
        )
    }
}