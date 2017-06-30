package tam.howard.itunessearch_kotlin.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tam.howard.itunessearch_kotlin.api.endpoint.ITunesApiEndpoint
import tam.howard.itunessearch_kotlin.config.Constant
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingResponseModel
import javax.inject.Singleton

/**
 * Created by Howard on 29/6/2017.
 */

@Singleton
class ApiManager {

    init {
        Log.d("ApiManager", "init")
    }

    val iTunesApiEndpoint: ITunesApiEndpoint

    init {
        val iTunesApiRetrofit = Retrofit.Builder()
                .baseUrl(Constant.ITUNES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        iTunesApiEndpoint = iTunesApiRetrofit.create(ITunesApiEndpoint::class.java)
    }

    fun getSearchResult(queryMap: HashMap<String, String>, callback: Callback<MusicListingResponseModel> ){
        val musicListingCall: Call<MusicListingResponseModel> = iTunesApiEndpoint.getSearchResult(queryMap)
        musicListingCall.enqueue(callback)
    }

}