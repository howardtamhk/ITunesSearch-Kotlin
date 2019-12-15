package tam.howard.itunessearch_kotlin.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingResponseModel

/**
 * Created by Howard on 29/6/2017.
 */
interface ITunesApiEndpoint {

    @GET("/search")
    fun getSearchResult(@QueryMap queryParameter: HashMap<String, String>): Observable<Response<MusicListingResponseModel>>
}