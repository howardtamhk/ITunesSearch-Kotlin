package tam.howard.itunessearch_kotlin.base

import android.content.Context
import androidx.databinding.Observable

/**
 * Created by Howard on 29/6/2017.
 */
interface BaseContract {

    interface BaseView{
    }

    interface BaseViewModel<V: BaseView> : Observable {
        fun onAttachView(view: V)

        fun onDetachView()

    }
}