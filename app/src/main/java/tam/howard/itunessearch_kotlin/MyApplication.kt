package tam.howard.itunessearch_kotlin

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import tam.howard.itunessearch_kotlin.di.DaggerApplicationComponent

/**
 *
 * Application class for the application
 *
 * Created by Howard on 29/6/2017.
 */
class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this)
    }
}