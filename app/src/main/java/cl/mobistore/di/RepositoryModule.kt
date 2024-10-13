package cl.mobistore.di

import cl.mobistore.repository.PhoneRepository
import cl.mobistore.repository.PhoneRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPhoneRepository(phoneRepositoryImp: PhoneRepositoryImp): PhoneRepository
}
