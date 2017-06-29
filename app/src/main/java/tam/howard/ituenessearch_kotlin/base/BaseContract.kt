package tam.howard.ituenessearch_kotlin.base

import android.databinding.Observable
import android.os.Bundle

/**
 * Created by Howard on 29/6/2017.
 */
interface BaseContract {

    interface BaseView

    interface BaseViewModel<V: BaseView> : Observable {
        fun onAttachView(view: V)

        fun onDetachView()

    }
}