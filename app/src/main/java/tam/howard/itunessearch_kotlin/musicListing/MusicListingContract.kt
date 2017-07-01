package tam.howard.itunessearch_kotlin.musicListing

import android.text.Editable
import android.view.View
import android.widget.EditText
import tam.howard.itunessearch_kotlin.base.BaseContract

/**
 * Created by Howard on 30/6/2017.
 */
interface MusicListingContract {

    interface MusicListingView: BaseContract.BaseView{

    }

    interface MusicListingViewModel: BaseContract.BaseViewModel<MusicListingView>{
        fun onClickSearchAction(keywords: EditText)

        fun resetMediaPlayer()

        fun releaseMediaPlayer()

        fun playingPosition(): Int
    }
}