package demo.at.ram.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import demo.at.ram.data.repository.OnePieceRepositoryImpl
import demo.at.ram.data.repository.CompositeCharacterRepositoryImpl
import demo.at.ram.data.repository.UserDataRepositoryImpl
import demo.at.ram.domain.repository.OnePieceRepository
import demo.at.ram.domain.repository.CompositeCharacterRepository
import demo.at.ram.domain.repository.UserDataRepository

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(
        characterRepo: OnePieceRepositoryImpl
    ): OnePieceRepository

    @Binds
    abstract fun bindUserDataRepository(
        userDataRepo: UserDataRepositoryImpl
    ): UserDataRepository

    @Binds
    abstract fun bindCompositeCharacterRepository(
        compositeRepo: CompositeCharacterRepositoryImpl
    ): CompositeCharacterRepository
}