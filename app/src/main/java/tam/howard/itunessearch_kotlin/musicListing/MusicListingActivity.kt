package tam.howard.itunessearch_kotlin.musicListing

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.content_music_listing.*

import tam.howard.itunessearch_kotlin.R
import tam.howard.itunessearch_kotlin.base.BaseActivity
import tam.howard.itunessearch_kotlin.databinding.ActivityMusicListingBinding

class MusicListingActivity : BaseActivity<ActivityMusicListingBinding, MusicListingViewModel>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(R.layout.activity_music_listing, MusicListingViewModel::class.java)
    }

    override fun onStop() {
        super.onStop()
        viewModel.resetMediaPlayer()
        resetListingPlayingIcon()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.releaseMediaPlayer()
    }

    override fun setupView() {
        setSupportActionBar(binding.toolbar)

        editText_music_search_search_keyword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.onClickSearchAction(v as EditText)
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        recyclerView_music_search_listing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_music_search_listing.adapter = (viewModel as MusicListingViewModel).musicListingItemAdapter
    }

    override fun subscribeLiveData() {
        viewModel.showNetworkError.observe(this, Observer {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
        })

        viewModel.showEmptyListUi.observe(this, Observer {
            Toast.makeText(this, getString(R.string.music_listing_no_result_matched), Toast.LENGTH_SHORT).show()
        })

    }

    fun resetListingPlayingIcon() {
        if (viewModel.playingPosition() < 0) {
            return
        }
        val musicListingItemViewHolder: MusicListingItemAdapter.MusicListingItemViewHolder? = (recyclerView_music_search_listing.findViewHolderForAdapterPosition(viewModel.playingPosition()) as? MusicListingItemAdapter.MusicListingItemViewHolder)
        musicListingItemViewHolder?.onOtherPlayButtonClick()
    }


}
