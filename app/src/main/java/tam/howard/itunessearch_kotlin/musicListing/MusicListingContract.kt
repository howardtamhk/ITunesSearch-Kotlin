package tam.howard.itunessearch_kotlin.musicListing

import android.text.Editable
import tam.howard.itunessearch_kotlin.base.BaseContract

/**
 * Created by Howard on 30/6/2017.
 */
interface MusicListingContract {

    interface MusicListingView: BaseContract.BaseView{

    }

    interface MusicListingViewModel: BaseContract.BaseViewModel<MusicListingView>{
        fun onClickSearchAction(keywords: String)
    }
}