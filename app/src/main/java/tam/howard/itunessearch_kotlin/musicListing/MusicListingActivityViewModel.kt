package tam.howard.itunessearch_kotlin.musicListing

import android.databinding.Bindable
import android.text.Editable
import android.view.View
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tam.howard.itunessearch_kotlin.api.ApiManager
import tam.howard.itunessearch_kotlin.base.BaseActivityViewModel
import tam.howard.itunessearch_kotlin.config.Constant
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingItemModel
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingResponseModel
import javax.inject.Inject

/**
 * Created by Howard on 30/6/2017.
 */
class MusicListingActivityViewModel @Inject constructor(private val apiManager: ApiManager) : BaseActivityViewModel<MusicListingContract.MusicListingView>(), MusicListingContract.MusicListingViewModel {


    val musicList: ArrayList<MusicListingItemModel> = ArrayList()

    override fun onClickSearchAction(keywords: String) {
        resetListing();

        val query: HashMap<String, String> = HashMap()
        query.put(Constant.ITUNES_API_PARAMETER_TERM_KEY, keywords)
        query.put(Constant.ITUNES_API_PARAMETER_MEDIA_KEY, Constant.ITUNES_API_PARAMETER_MEDIA_VALUE)
        query.put(Constant.ITUNES_API_PARAMETER_LIMIT_KEY, Constant.LISTING_PAGE_SIZE.toString())

        callSearchApi(query)
    }

    private fun resetListing() {

    }

    fun callSearchApi(queryMap: HashMap<String, String>) {
        apiManager.getSearchResult(queryMap, object : Callback<MusicListingResponseModel> {
            override fun onResponse(call: Call<MusicListingResponseModel>?, response: Response<MusicListingResponseModel>?) {
                if (response == null || !response.isSuccessful || response.body() == null) {
                    setupNetworkErrorView()
                    return
                }

                musicList.addAll(response.body()!!.results)
            }

            override fun onFailure(call: Call<MusicListingResponseModel>?, t: Throwable?) {
                setupNetworkErrorView()
            }

        })
    }

    private fun setupNetworkErrorView() {
        view?.let { Toast.makeText(it.getActivityContext(), "Network Error", Toast.LENGTH_SHORT) }
    }

}