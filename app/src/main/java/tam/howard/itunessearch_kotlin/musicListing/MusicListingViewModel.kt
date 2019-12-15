package tam.howard.itunessearch_kotlin.musicListing

import android.content.Context
import android.media.MediaPlayer
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import tam.howard.itunessearch_kotlin.api.ITunesApiEndpoint
import tam.howard.itunessearch_kotlin.api.call
import tam.howard.itunessearch_kotlin.base.BaseViewModel
import tam.howard.itunessearch_kotlin.base.extension.addTo
import tam.howard.itunessearch_kotlin.config.Constant
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingItemModel
import javax.inject.Inject

/**
 * Created by Howard on 30/6/2017.
 */
class MusicListingViewModel @Inject constructor(private val iTunesApiEndpoint: ITunesApiEndpoint) : BaseViewModel() {
    val musicList: ArrayList<MusicListingItemModel> = ArrayList()


    val mediaPlayer: MediaPlayer = MediaPlayer()
    var playingPosition: Int = -1

    val onMusicListUpdate: MutableLiveData<ArrayList<MusicListingItemModel>> = MutableLiveData()
    val showNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    val showEmptyListUi: MutableLiveData<Boolean> = MutableLiveData()


    fun onClickSearchAction(editText: EditText) {
        resetListing()

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
        iTunesApiEndpoint.getSearchResult(queryMap).call().subscribe({
            when (it.results.size){
                0 -> showEmptyListUi.postValue(true)
                else -> {
                    musicList.addAll(it.results)
                    onMusicListUpdate.postValue(musicList)
                }
            }
        }, {
            showNetworkError.postValue(true)
        }).addTo(this.disposables)
    }

    fun playingPosition(): Int {
        return playingPosition
    }

    fun releaseMediaPlayer() {
        resetMediaPlayer()
        mediaPlayer.release()
    }

    fun resetMediaPlayer(){
        mediaPlayer.stop()
        mediaPlayer.reset()
    }


    private fun hideSoftKeyboard(editText: EditText) {
        val inputManager:InputMethodManager = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
        inputManager.hideSoftInputFromWindow(editText.applicationWindowToken, 0)
    }
}