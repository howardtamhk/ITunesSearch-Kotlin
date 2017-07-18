package tam.howard.itunessearch_kotlin.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import tam.howard.itunessearch_kotlin.MyApplication

import tam.howard.itunessearch_kotlin.R
import tam.howard.itunessearch_kotlin.base.BaseActivity
import tam.howard.itunessearch_kotlin.databinding.ActivityTestDaggerSingletonBinding
import tam.howard.itunessearch_kotlin.test.di.DaggerTestDaggerSingletonComponent
import tam.howard.itunessearch_kotlin.test.di.TestDaggerSingletonComponent

class TestDaggerSingletonActivity : BaseActivity<ActivityTestDaggerSingletonBinding, TestDaggerSingletonContract.TestDaggerSingletonViewModel>(), TestDaggerSingletonContract.TestDaggerSingletonView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
        bindContentView(R.layout.activity_test_dagger_singleton)

        viewModel.testCall()
    }

    private fun injectDagger(){
        val testDaggerSingletonComponent: TestDaggerSingletonComponent = DaggerTestDaggerSingletonComponent.builder()
                .apiComponent(MyApplication.apiComponent)
                .build()

        testDaggerSingletonComponent.inject(this)
    }
}
