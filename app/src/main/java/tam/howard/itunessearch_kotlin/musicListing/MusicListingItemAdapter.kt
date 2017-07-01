package tam.howard.itunessearch_kotlin.musicListing

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import tam.howard.itunessearch_kotlin.R
import tam.howard.itunessearch_kotlin.base.BaseViewHolder
import tam.howard.itunessearch_kotlin.databinding.CardMusicListingItemBinding
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingItemModel

/**
 * Created by Howard on 1/7/2017.
 */
class MusicListingItemAdapter(private val musicList: ArrayList<MusicListingItemModel>): RecyclerView.Adapter<MusicListingItemAdapter.MusicListingItemViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MusicListingItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_music_listing_item, viewGroup, false)
        return MusicListingItemViewHolder(view)
    }

    override fun getItemCount(): Int = musicList.size


    override fun onBindViewHolder(musicListingItemViewHolder: MusicListingItemViewHolder, position: Int) {
        val musicListingModel: MusicListingItemModel = musicList.get(position)
        musicListingItemViewHolder.bindData(musicListingModel)
    }





    class MusicListingItemViewHolder(itemView: View) : BaseViewHolder<CardMusicListingItemBinding, MusicListingViewModelImpl>(itemView){

        fun bindData(musicListingModel: MusicListingItemModel){

            binding.item = musicListingModel
            binding.executePendingBindings()

            Picasso.with(binding.imageViewMusicListingItemSongPhoto.context).load(musicListingModel.image).into(binding.imageViewMusicListingItemSongPhoto)
        }


    }

}