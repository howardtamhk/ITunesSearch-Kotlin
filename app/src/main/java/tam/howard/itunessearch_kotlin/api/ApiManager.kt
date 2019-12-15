package tam.howard.itunessearch_kotlin.api

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class ApiException(errorCode: Int?, errorBodyStr: String?) : Throwable()

fun <T> Observable<Response<T>>.call(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .switchMap { response ->
                Observable.create<T> { observer ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            observer.onNext(it)
                        } ?: kotlin.run {
                            observer.onError(ApiException(null, "Response body is null"))
                        }
                    } else {
                        observer.onError(ApiException(response.code(), response.errorBody()?.string()))

                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
}