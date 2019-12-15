package tam.howard.itunessearch_kotlin.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import tam.howard.itunessearch_kotlin.BR
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseContract.BaseViewModel<*>> : AppCompatActivity(), BaseContract.BaseView {

    protected lateinit var binding: B
    @Inject protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun bindContentView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.vm, viewModel)

        (viewModel as BaseContract.BaseViewModel<BaseContract.BaseView>).onAttachView(this)
    }

    fun getActivityContext(): Context {
        return this
    }
}
