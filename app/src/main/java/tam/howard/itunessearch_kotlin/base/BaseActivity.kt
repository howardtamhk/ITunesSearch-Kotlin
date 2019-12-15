package tam.howard.itunessearch_kotlin.base

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import tam.howard.itunessearch_kotlin.BR
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: B
    protected lateinit var viewModel: VM



    fun init(@LayoutRes layoutRes: Int, vmClass: Class<VM>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[vmClass]

        bindContentView(layoutRes)

        setupView()
        subscribeLiveData()
    }

    private fun bindContentView(@LayoutRes layoutRes: Int) {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.setVariable(BR.vm, viewModel)
        binding.lifecycleOwner = this

    }

    open fun setupView() {}

    open fun subscribeLiveData() {}
}
