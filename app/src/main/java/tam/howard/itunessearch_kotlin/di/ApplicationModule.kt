package tam.howard.itunessearch_kotlin.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tam.howard.itunessearch_kotlin.api.ITunesApiEndpoint
import tam.howard.itunessearch_kotlin.config.Constant
import javax.inject.Singleton


@Module
object ApplicationModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideITunesApiEndpoint(): ITunesApiEndpoint {
        return Retrofit.Builder()
                .baseUrl(Constant.ITUNES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ITunesApiEndpoint::class.java)
    }
}