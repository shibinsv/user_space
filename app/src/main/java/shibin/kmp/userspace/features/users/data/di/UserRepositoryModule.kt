package shibin.kmp.userspace.features.users.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shibin.kmp.userspace.features.users.data.repository.UserRepositoryImpl
import shibin.kmp.userspace.features.users.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(implementation: UserRepositoryImpl): UserRepository
}