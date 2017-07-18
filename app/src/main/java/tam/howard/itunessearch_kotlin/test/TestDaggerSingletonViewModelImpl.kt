package tam.howard.itunessearch_kotlin.test

import android.util.Log
import tam.howard.itunessearch_kotlin.api.ApiManager
import tam.howard.itunessearch_kotlin.base.BaseViewModelImpl
import javax.inject.Inject

/**
 * Created by Howard on 18/7/2017.
 */
class TestDaggerSingletonViewModelImpl @Inject constructor(private val apiManager: ApiManager): BaseViewModelImpl<TestDaggerSingletonContract.TestDaggerSingletonView>(), TestDaggerSingletonContract.TestDaggerSingletonViewModel{
    override fun testCall() {
        Log.d("ABC", "DEF")
    }

}