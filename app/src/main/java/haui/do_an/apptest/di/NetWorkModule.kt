package haui.do_an.apptest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://us1.locationiq.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}