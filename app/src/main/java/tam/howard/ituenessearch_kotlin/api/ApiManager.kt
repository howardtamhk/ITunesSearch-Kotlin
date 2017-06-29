package tam.howard.ituenessearch_kotlin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tam.howard.ituenessearch_kotlin.api.endpoint.ITunesApiEndpoint
import tam.howard.ituenessearch_kotlin.config.Constant
import javax.inject.Singleton

/**
 * Created by Howard on 29/6/2017.
 */

@Singleton
class ApiManager {

    val iTunesApiEndpoint: ITunesApiEndpoint

    init {
        val iTunesApiRetrofit = Retrofit.Builder()
                .baseUrl(Constant.ITUNES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        iTunesApiEndpoint = iTunesApiRetrofit.create(ITunesApiEndpoint::class.java)
    }


}