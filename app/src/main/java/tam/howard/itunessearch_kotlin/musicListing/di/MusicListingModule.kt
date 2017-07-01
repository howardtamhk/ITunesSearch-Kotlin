package tam.howard.itunessearch_kotlin.musicListing.di

import dagger.Module
import dagger.Provides
import tam.howard.itunessearch_kotlin.di.ActivityScopes
import tam.howard.itunessearch_kotlin.musicListing.MusicListingViewModelImpl
import tam.howard.itunessearch_kotlin.musicListing.MusicListingContract
import tam.howard.itunessearch_kotlin.musicListing.MusicListingItemAdapter

/**
 * Created by Howard on 30/6/2017.
 */

@Module
class MusicListingModule {

    @Provides
    @ActivityScopes
    internal fun providesMusicListingViewModel(musicListingActivityViewModel: MusicListingViewModelImpl): MusicListingContract.MusicListingViewModel {
        return musicListingActivityViewModel
    }

}