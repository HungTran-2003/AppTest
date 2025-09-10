package haui.do_an.apptest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import haui.do_an.apptest.data.api.LocationIQService

import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideLocationIQService(retrofit: Retrofit): LocationIQService {
        return retrofit.create(LocationIQService::class.java)
    }

}