package tam.howard.itunessearch_kotlin.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import tam.howard.itunessearch_kotlin.BR

/**
 * Created by Howard on 1/7/2017.
 */
abstract class BaseViewHolder<B: ViewDataBinding, VM : BaseContract.BaseViewModel<*>> (itemView: View):RecyclerView.ViewHolder(itemView) {

    protected lateinit var binding: B


    init {
        bindContentView();
    }

    private fun bindContentView() {
        binding = DataBindingUtil.bind(itemView)
    }


}