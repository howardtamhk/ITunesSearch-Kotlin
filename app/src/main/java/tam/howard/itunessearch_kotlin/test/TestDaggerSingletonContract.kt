package tam.howard.itunessearch_kotlin.test

import tam.howard.itunessearch_kotlin.base.BaseContract

/**
 * Created by Howard on 18/7/2017.
 */
interface TestDaggerSingletonContract {

    interface TestDaggerSingletonView: BaseContract.BaseView{

    }

    interface TestDaggerSingletonViewModel: BaseContract.BaseViewModel<TestDaggerSingletonView>{
        fun testCall()
    }
}