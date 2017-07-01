package tam.howard.itunessearch_kotlin.musicListing

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import kotlinx.android.synthetic.main.content_music_listing.*
import tam.howard.itunessearch_kotlin.MyApplication

import tam.howard.itunessearch_kotlin.R
import tam.howard.itunessearch_kotlin.base.BaseActivity
import tam.howard.itunessearch_kotlin.databinding.ActivityMusicListingBinding
import tam.howard.itunessearch_kotlin.musicListing.di.DaggerMusicListingComponent
import tam.howard.itunessearch_kotlin.musicListing.di.MusicListingComponent

class MusicListingActivity : BaseActivity<ActivityMusicListingBinding, MusicListingContract.MusicListingViewModel>(), MusicListingContract.MusicListingView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
        bindContentView(R.layout.activity_music_listing)
        setSupportActionBar(binding.toolbar)

        setupView()

    }

    private fun injectDagger() {
        val musicListingComponent: MusicListingComponent = DaggerMusicListingComponent.builder()
                .apiComponent(MyApplication.apiComponent)
                .build()

        musicListingComponent.inject(this)

    }

    private fun setupView() {
        editText_music_search_search_keyword.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH){
                viewModel.onClickSearchAction(v as EditText)
                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

        recyclerView_music_search_listing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView_music_search_listing.adapter = (viewModel as MusicListingViewModelImpl).musicListingItemAdapter
    }

}
