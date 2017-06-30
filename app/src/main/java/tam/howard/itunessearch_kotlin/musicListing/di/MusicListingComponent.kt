package tam.howard.itunessearch_kotlin.musicListing.di

import dagger.Component
import tam.howard.itunessearch_kotlin.api.di.ApiComponent
import tam.howard.itunessearch_kotlin.di.ActivityScopes
import tam.howard.itunessearch_kotlin.musicListing.MusicListingActivity

/**
 * Created by Howard on 30/6/2017.
 */

@ActivityScopes
@Component(dependencies = arrayOf(ApiComponent::class), modules = arrayOf(MusicListingModule::class))
interface MusicListingComponent {

    fun inject (activity: MusicListingActivity)
}