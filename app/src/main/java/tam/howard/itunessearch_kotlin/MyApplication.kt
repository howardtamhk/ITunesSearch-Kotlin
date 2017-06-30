package tam.howard.itunessearch_kotlin

import android.app.Application
import tam.howard.itunessearch_kotlin.api.di.ApiComponent
import tam.howard.itunessearch_kotlin.api.di.DaggerApiComponent

/**
 *
 * Application class for the application
 *
 * Created by Howard on 29/6/2017.
 */
class MyApplication : Application() {


    companion object {
        lateinit var apiComponent: ApiComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        apiComponent = DaggerApiComponent.builder().build()

    }
}