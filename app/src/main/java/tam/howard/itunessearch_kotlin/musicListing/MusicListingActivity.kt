package tam.howard.itunessearch_kotlin.musicListing

import android.os.Bundle
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

        setupListingView()

    }

    private fun injectDagger() {
        val musicListingComponent: MusicListingComponent = DaggerMusicListingComponent.builder()
                .apiComponent(MyApplication.apiComponent)
                .build()

        musicListingComponent.inject(this)

    }

    private fun setupListingView() {

    }

}
