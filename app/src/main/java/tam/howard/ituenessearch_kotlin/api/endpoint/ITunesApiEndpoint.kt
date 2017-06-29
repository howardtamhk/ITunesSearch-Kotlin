package tam.howard.ituenessearch_kotlin.api.endpoint

import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Howard on 29/6/2017.
 */
interface ITunesApiEndpoint {

    @GET("/search")
    fun getSearchResult(@QueryMap queryParameter: HashMap<String, String>)
}