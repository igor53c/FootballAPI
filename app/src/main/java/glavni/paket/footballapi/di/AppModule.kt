package glavni.paket.footballapi.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import glavni.paket.footballapi.data.api.remote.TeamApi
import glavni.paket.footballapi.repository.TeamRepository
import glavni.paket.footballapi.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTeamRepository(
        api: TeamApi
    ) = TeamRepository(api)

    @Singleton
    @Provides
    fun provideTeamApi(): TeamApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(TeamApi::class.java)
    }
}