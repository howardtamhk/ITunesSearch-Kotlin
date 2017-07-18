package tam.howard.itunessearch_kotlin.test.di

import dagger.Module
import dagger.Provides
import tam.howard.itunessearch_kotlin.di.ActivityScopes
import tam.howard.itunessearch_kotlin.musicListing.MusicListingContract
import tam.howard.itunessearch_kotlin.musicListing.MusicListingViewModelImpl
import tam.howard.itunessearch_kotlin.test.TestDaggerSingletonContract
import tam.howard.itunessearch_kotlin.test.TestDaggerSingletonViewModelImpl

/**
 * Created by Howard on 18/7/2017.
 */

@Module
class TestDaggerSingletonModule {

    @Provides
    @ActivityScopes
    internal fun providesTestDaggerSingletonViewModel(testDaggerSingletonViewModelImpl: TestDaggerSingletonViewModelImpl): TestDaggerSingletonContract.TestDaggerSingletonViewModel {
        return testDaggerSingletonViewModelImpl
    }
}