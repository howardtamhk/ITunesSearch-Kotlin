package tam.howard.itunessearch_kotlin.musicListing

import android.media.AudioAttributes
import android.media.AudioManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import tam.howard.itunessearch_kotlin.R
import tam.howard.itunessearch_kotlin.base.BaseViewHolder
import tam.howard.itunessearch_kotlin.base.extension.gone
import tam.howard.itunessearch_kotlin.base.extension.setRemoteImage
import tam.howard.itunessearch_kotlin.base.extension.visible
import tam.howard.itunessearch_kotlin.databinding.CardMusicListingItemBinding
import tam.howard.itunessearch_kotlin.musicListing.model.MusicListingItemModel

/**
 * Created by Howard on 1/7/2017.
 */
class MusicListingItemAdapter(private val musicList: ArrayList<MusicListingItemModel>, private val musicListingViewModel: MusicListingViewModel) : ListAdapter<MusicListingItemModel, MusicListingItemAdapter.MusicListingItemViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MusicListingItemViewHolder {
        val binding = DataBindingUtil.inflate<CardMusicListingItemBinding>(LayoutInflater.from(viewGroup.context), R.layout.card_music_listing_item, viewGroup, false)
        val musicListingItemViewHolder = MusicListingItemViewHolder(binding, musicListingViewModel)
        return musicListingItemViewHolder
    }

    override fun getItemCount(): Int = musicList.size


    override fun onBindViewHolder(musicListingItemViewHolder: MusicListingItemViewHolder, position: Int) {
        val musicListingModel: MusicListingItemModel = musicList.get(position)
        musicListingItemViewHolder.bindData(position, musicListingModel)
    }


    class MusicListingItemViewHolder(binding: CardMusicListingItemBinding, private val musicListingViewModel: MusicListingViewModel) : BaseViewHolder<CardMusicListingItemBinding>(binding) {

        val mediaPlayer = musicListingViewModel.mediaPlayer
        var playing: Boolean = false


        fun bindData(position: Int, musicListingModel: MusicListingItemModel) {

            if (position != musicListingViewModel.playingPosition) {
                playing = false
                setPlayIcon()
            } else {
                playing = true
                setStopIcon()
            }

            binding.item = musicListingModel
            binding.executePendingBindings()

            binding.imageViewMusicListingItemSongPhoto?.setRemoteImage(musicListingModel.image)

            binding.imageBtnMusicListingPlay?.setOnClickListener {
                if (playing) {
                    resetPlayingState()
                    setPlayIcon()
                } else {
                    startPlay(musicListingModel.previewUrl)
                }
            }
        }

        private fun startPlay(previewUrl: String) {

            binding.imageBtnMusicListingPlay?.gone()
            binding.progressBarMusicListingPlay?.visible()

            if (mediaPlayer.isPlaying) {
                resetPlayingState()
                (binding.imageBtnMusicListingPlay?.context as MusicListingActivity).resetListingPlayingIcon()
            }

            mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
            mediaPlayer.setDataSource(previewUrl)
            mediaPlayer.setOnPreparedListener {
                binding.imageBtnMusicListingPlay?.visible()
                binding.progressBarMusicListingPlay?.gone()
                setStopIcon()
                it.start()

                it.setOnCompletionListener {
                    resetPlayingState()
                    setPlayIcon()
                }
            }
            mediaPlayer.prepareAsync()
            playing = true
            musicListingViewModel.playingPosition = layoutPosition
        }

        fun resetPlayingState() {
            musicListingViewModel.resetMediaPlayer()
            playing = false
        }

        fun onOtherPlayButtonClick() {
            playing = false
            setPlayIcon()
        }

        private fun setPlayIcon() {
            binding.imageBtnMusicListingPlay?.setImageResource(R.drawable.ic_play_arrow_black)
        }

        private fun setStopIcon() {
            binding.imageBtnMusicListingPlay?.setImageResource(R.drawable.ic_stop_black)
        }


    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<MusicListingItemModel>() {
            override fun areItemsTheSame(oldItem: MusicListingItemModel, newItem: MusicListingItemModel): Boolean =
                    oldItem.previewUrl == newItem.previewUrl


            override fun areContentsTheSame(oldItem: MusicListingItemModel, newItem: MusicListingItemModel): Boolean =
                    oldItem == newItem


        }
    }

}
