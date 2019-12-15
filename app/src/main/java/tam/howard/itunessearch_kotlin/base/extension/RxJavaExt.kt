package tam.howard.itunessearch_kotlin.base.extension

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(disposables: ArrayList<Disposable>) {
    disposables.add(this)
}