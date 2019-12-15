package tam.howard.itunessearch_kotlin.musicListing

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
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
class MusicListingItemAdapter(private val musicList: ArrayList<MusicListingItemModel>, private val musicListingViewModelImpl: MusicListingViewModelImpl) : RecyclerView.Adapter<MusicListingItemAdapter.MusicListingItemViewHolder>() {

    val viewHolderList: ArrayList<MusicListingItemViewHolder> = ArrayList()


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MusicListingItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_music_listing_item, viewGroup, false)
        val musicListingItemViewHolder = MusicListingItemViewHolder(view, musicListingViewModelImpl)
        viewHolderList.add(musicListingItemViewHolder)
        return musicListingItemViewHolder
    }

    override fun getItemCount(): Int = musicList.size


    override fun onBindViewHolder(musicListingItemViewHolder: MusicListingItemViewHolder, position: Int) {
        val musicListingModel: MusicListingItemModel = musicList.get(position)
        musicListingItemViewHolder.bindData(position, musicListingModel)
    }


    class MusicListingItemViewHolder(itemView: View, private val musicListingViewModelImpl: MusicListingViewModelImpl) : BaseViewHolder<CardMusicListingItemBinding, MusicListingViewModelImpl>(itemView) {

        val mediaPlayer = musicListingViewModelImpl.mediaPlayer
        var playing: Boolean = false


        fun bindData(position: Int, musicListingModel: MusicListingItemModel) {

            if (position != musicListingViewModelImpl.playingPosition) {
                Log.d("TEst", "$position ${musicListingViewModelImpl.playingPosition}")
                playing = false
                setPlayIcon()
            } else {
                playing = true
                setStopIcon()
            }

            binding?.item = musicListingModel
            binding?.executePendingBindings()

            Picasso.with(binding?.imageViewMusicListingItemSongPhoto?.context).load(musicListingModel.image).into(binding?.imageViewMusicListingItemSongPhoto)

            binding?.imageBtnMusicListingPlay?.setOnClickListener {
                if (playing) {
                    resetPlayingState()
                    setPlayIcon()
                } else {
                    startPlay(musicListingModel.previewUrl)
                }
            }
        }

        private fun startPlay(previewUrl: String) {

            binding?.imageBtnMusicListingPlay?.visibility = View.GONE
            binding?.progressBarMusicListingPlay?.visibility = View.VISIBLE

            if (mediaPlayer.isPlaying) {
                resetPlayingState()
                (binding?.imageBtnMusicListingPlay?.context as MusicListingActivity).resetListingPlayingIcon()
            }

            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(previewUrl);
            mediaPlayer.setOnPreparedListener {
                binding?.imageBtnMusicListingPlay?.visibility = View.VISIBLE
                binding?.progressBarMusicListingPlay?.visibility = View.GONE
                setStopIcon()
                it.start()

                it.setOnCompletionListener {
                    resetPlayingState()
                    setPlayIcon()
                }
            }
            mediaPlayer.prepareAsync()
            playing = true
            musicListingViewModelImpl.playingPosition = layoutPosition
        }

        fun resetPlayingState() {
            musicListingViewModelImpl.resetMediaPlayer()
            playing = false
        }

        fun onOtherPlayButtonClick(){
            playing = false
            setPlayIcon()
        }

        private fun setPlayIcon() {
            binding?.imageBtnMusicListingPlay?.setImageResource(R.drawable.ic_play_arrow_black)
        }

        private fun setStopIcon() {
            binding?.imageBtnMusicListingPlay?.setImageResource(R.drawable.ic_stop_black)
        }


    }

}
