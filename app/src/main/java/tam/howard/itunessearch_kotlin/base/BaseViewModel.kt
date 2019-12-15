package tam.howard.itunessearch_kotlin.base

import androidx.databinding.BaseObservable
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

/**
 * Created by Howard on 29/6/2017.
 */
abstract class BaseViewModel: ViewModel() {

    val disposables: ArrayList<Disposable> = arrayListOf()

    override fun onCleared() {
        super.onCleared()
        disposables.forEach {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }
}