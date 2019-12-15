package tam.howard.itunessearch_kotlin.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import tam.howard.itunessearch_kotlin.BR

/**
 * Created by Howard on 1/7/2017.
 */
abstract class BaseViewHolder<B: ViewDataBinding> (protected val binding: B): RecyclerView.ViewHolder(binding.root)