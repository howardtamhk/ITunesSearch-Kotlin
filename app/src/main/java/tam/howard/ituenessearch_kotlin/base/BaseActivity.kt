package tam.howard.ituenessearch_kotlin.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import tam.howard.ituenessearch_kotlin.BR
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseActivityViewModel<BaseContract.BaseView>> : AppCompatActivity(), BaseContract.BaseView {

    protected lateinit var binding: B
    @Inject protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun bindContentView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.vm, viewModel)

        viewModel.onAttachView(this)
    }
}
