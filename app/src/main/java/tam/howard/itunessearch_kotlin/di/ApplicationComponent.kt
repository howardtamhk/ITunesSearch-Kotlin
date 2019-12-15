package tam.howard.itunessearch_kotlin.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import tam.howard.itunessearch_kotlin.MyApplication
import tam.howard.itunessearch_kotlin.musicListing.di.MusicListingModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    MusicListingModule::class
])
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}