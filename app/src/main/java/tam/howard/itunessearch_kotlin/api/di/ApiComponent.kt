package tam.howard.itunessearch_kotlin.api.di

import dagger.Component
import tam.howard.itunessearch_kotlin.api.ApiManager
import javax.inject.Singleton

/**
 * Created by Howard on 1/7/2017.
 */

@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun apiManager(): ApiManager
}