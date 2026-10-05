package shibin.kmp.userspace.di


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shibin.kmp.userspace.domain.repository.UserRepository
import shibin.kmp.userspace.data.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
