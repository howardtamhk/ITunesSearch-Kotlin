package tam.howard.itunessearch_kotlin.musicListing.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tam.howard.itunessearch_kotlin.di.ViewModelKey
import tam.howard.itunessearch_kotlin.di.ViewModelModule
import tam.howard.itunessearch_kotlin.musicListing.MusicListingActivity
import tam.howard.itunessearch_kotlin.musicListing.MusicListingViewModel

@Module
abstract class MusicListingModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    internal abstract fun musicListingActivity(): MusicListingActivity

    @Binds
    @IntoMap
    @ViewModelKey(MusicListingViewModel::class)
    abstract fun bindMusicListingViewModel(viewModel: MusicListingViewModel): ViewModel
}

