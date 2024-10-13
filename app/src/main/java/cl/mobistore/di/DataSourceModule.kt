package cl.mobistore.di

import android.content.Context
import androidx.room.Room
import cl.mobistore.datasource.PhoneDbDataSource
import cl.mobistore.datasource.PhoneRestDataSource
import cl.mobistore.model.PhoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/Himuravidal/FakeAPIdata/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePhoneRestDataSource(retrofit: Retrofit): PhoneRestDataSource =
        retrofit.create(PhoneRestDataSource::class.java)

    @Singleton
    @Provides
    fun providePhoneDbDataSource(@ApplicationContext context: Context): PhoneDbDataSource {
        return Room.databaseBuilder(
            context,
            PhoneDbDataSource::class.java,
            "phone_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePhoneDao(db: PhoneDbDataSource): PhoneDao = db.phoneDao()
}
