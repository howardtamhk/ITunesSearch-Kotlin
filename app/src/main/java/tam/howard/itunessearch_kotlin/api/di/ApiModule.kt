package tam.howard.itunessearch_kotlin.api.di

import dagger.Module
import dagger.Provides
import tam.howard.itunessearch_kotlin.api.ApiManager
import javax.inject.Singleton

/**
 * Created by Howard on 30/6/2017.
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun providesApiManager(): ApiManager {
        return ApiManager()
    }

}