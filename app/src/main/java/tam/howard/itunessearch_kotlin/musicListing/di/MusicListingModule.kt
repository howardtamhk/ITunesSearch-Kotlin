package tam.howard.itunessearch_kotlin.musicListing.di

import dagger.Module
import dagger.Provides
import tam.howard.itunessearch_kotlin.di.ActivityScopes
import tam.howard.itunessearch_kotlin.musicListing.MusicListingActivityViewModel
import tam.howard.itunessearch_kotlin.musicListing.MusicListingContract

/**
 * Created by Howard on 30/6/2017.
 */

@Module
class MusicListingModule {

    @Provides
    @ActivityScopes
    internal fun providesMusicListingViewModel(musicListingActivityViewModel: MusicListingActivityViewModel): MusicListingContract.MusicListingViewModel {
        return musicListingActivityViewModel
    }
}