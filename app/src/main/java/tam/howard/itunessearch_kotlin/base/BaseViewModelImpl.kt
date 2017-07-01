package tam.howard.itunessearch_kotlin.base

import android.databinding.BaseObservable
import android.support.annotation.CallSuper

/**
 * Created by Howard on 29/6/2017.
 */
abstract class BaseViewModelImpl<V : BaseContract.BaseView> : BaseObservable(), BaseContract.BaseViewModel<V> {

    var view: V? = null
        private set

    @CallSuper
    override fun onAttachView(view: V) {
        this.view = view
    }

    @CallSuper
    override fun onDetachView() {
        view = null
    }
}