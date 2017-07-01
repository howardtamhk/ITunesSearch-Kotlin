package tam.howard.itunessearch_kotlin.musicListing

import android.content.Context
import android.hardware.input.InputManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tam.howard.itunessearch_kotlin.api.ApiManager
import tam.howard.itunessearch_kotlin.base.BaseViewModelImpl
import tam.howard.itunessearch_kotlin.config.Constant
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingItemModel
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingResponseModel
import javax.inject.Inject

/**
 * Created by Howard on 30/6/2017.
 */
class MusicListingViewModelImpl @Inject constructor(private val apiManager: ApiManager) : BaseViewModelImpl<MusicListingContract.MusicListingView>(), MusicListingContract.MusicListingViewModel {


    val musicList: ArrayList<MusicListingItemModel> = ArrayList()
    val musicListingItemAdapter: MusicListingItemAdapter

    init {
        musicListingItemAdapter = MusicListingItemAdapter(musicList)
    }

    override fun onClickSearchAction(editText: EditText) {
        resetListing();

        hideSoftKeyboard(editText)

        val query: HashMap<String, String> = HashMap()
        query.put(Constant.ITUNES_API_PARAMETER_TERM_KEY, editText.text.toString())
        query.put(Constant.ITUNES_API_PARAMETER_MEDIA_KEY, Constant.ITUNES_API_PARAMETER_MEDIA_VALUE)
        query.put(Constant.ITUNES_API_PARAMETER_LIMIT_KEY, Constant.LISTING_PAGE_SIZE.toString())

        callSearchApi(query)
    }

    private fun resetListing() {
        musicList.clear()
    }

    fun callSearchApi(queryMap: HashMap<String, String>) {
        apiManager.getSearchResult(queryMap, object : Callback<MusicListingResponseModel> {
            override fun onResponse(call: Call<MusicListingResponseModel>?, response: Response<MusicListingResponseModel>?) {
                if (response == null || !response.isSuccessful || response.body() == null) {
                    setupNetworkErrorView()
                    return
                }

                val returnList = response.body()!!.results
                when (returnList.size){
                    0 -> setUpEmptyListView()
                    else -> musicList.addAll(response.body()!!.results)
                }

                musicListingItemAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MusicListingResponseModel>?, t: Throwable?) {
                setupNetworkErrorView()
            }

        })
    }

    private fun hideSoftKeyboard(editText: EditText) {
        val inputManager:InputMethodManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
        inputManager.hideSoftInputFromWindow(editText.applicationWindowToken, 0)
    }

    private fun setupNetworkErrorView() {
        (view as MusicListingActivity).let { Toast.makeText(it.getActivityContext(), "Network Error", Toast.LENGTH_SHORT).show() }
    }

    private fun setUpEmptyListView(){
        (view as MusicListingActivity).let { Toast.makeText(it.getActivityContext(), "Empty list result", Toast.LENGTH_SHORT).show() }
    }

}